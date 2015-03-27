package com.lynxspa.sdm.core.services.quartz.jobs.notifications;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.lynxspa.sdm.core.model.notifications.DeviceNotificationInstance;
import com.lynxspa.sdm.core.services.PersistenceService;
import com.lynxspa.sdm.core.services.notification.NotificationService;
import com.lynxspa.sdm.core.services.notification.dispatchers.api.NotificationDispatcher;
import com.lynxspa.sdm.core.services.quartz.api.SdmBasicJob;
import com.lynxspa.sdm.core.services.quartz.api.PropertyBean;
import com.lynxspa.sdm.core.services.quartz.api.TaskExecutionBean;
import com.lynxspa.sdm.core.services.quartz.api.exceptions.ExecutionException;
import com.lynxspa.sdm.core.services.quartz.jobs.notifications.beans.NotificationRefBean;

public class SendNotifications extends SdmBasicJob {
	@Autowired
	private PersistenceService	persistenceService;

	@Autowired
	private NotificationService	notificationService;

	private static final int	FETCH_SIZE	= 2000;
	private static final Logger	logger		= Logger.getLogger(SendNotifications.class);

	private void initializeExecutionVariables(TaskExecutionBean state) {
		for (PropertyBean p : getPropertyBeans()) {
			state.setExecutionVariable(p, state.getProperty(p));
		}
	}

	@Override
	protected void executeJob(TaskExecutionBean state) throws ExecutionException {
		logger.info("SendNotifications started... ");
		initializeExecutionVariables(state);

		Session session = null;

		try {
			session = persistenceService.openSession();
			session.beginTransaction();

			//@(format off)
			Query q = session.createQuery(
			"select new com.lynxspa.sdm.core.services.quartz.jobs.notifications.beans.NotificationRefBean(instance.id, PushNotificationToken.value, instance.notification.category, instance.notification.text) from DeviceNotificationInstance instance " +
			"	left join instance.device as device " +
			"	left join device.customerProperties as PushNotificationToken " +
			"		with PushNotificationToken.name = :idType " +
			"		and  PushNotificationToken.value is not null " +
			"	left join PushNotificationToken.customer as customer " +
			"	left join customer.settings as flagSupNotif " +
			"		with flagSupNotif.name = :flagCustomerSupressNotifications " +
			"	left join device.settings as flagSendNotification " +
			"		with flagSendNotification.name = :flagDeviceNotifiable " +
			"	where instance.sent = false " +
			"		and (flagSendNotification.value = 'true' or flagSendNotification.value is null) " +
			"		and (flagSupNotif.value = 'false' or flagSupNotif.value is null) "
			);
			//@(format on)

			q.setParameter("idType", "PushNotificationToken");
			q.setParameter("flagDeviceNotifiable", "IsNotifiable");
			q.setParameter("flagCustomerSupressNotifications", "SupressNotifications");

			q.setFetchSize(FETCH_SIZE);

			Map<String, NotificationDispatcher> dispatchers = new TreeMap<String, NotificationDispatcher>();
			
			@SuppressWarnings("unchecked")
			Iterator<NotificationRefBean> it = q.iterate();
			while (it.hasNext()) {
				NotificationRefBean entry = it.next();
				DeviceNotificationInstance instance = (DeviceNotificationInstance) session.get(DeviceNotificationInstance.class, entry.getInstanceId());
				
				NotificationDispatcher dispatcher = null;
				if (dispatchers.containsKey(instance.getDevice().getType())) {
					dispatcher = dispatchers.get(instance.getDevice().getType());
				} else {
					dispatcher = notificationService.getDispatcherForNotificationIntance(instance);
					
					if (dispatcher == null) {
						logger.warn("Unknown notification configuration for instance " + entry.getInstanceId());
						continue;
					}
					dispatchers.put(instance.getDevice().getType(), dispatcher);
				}
				dispatcher.dispatch(session, instance);
			}
		} catch (Throwable e) {
			logger.error("Failed!", e);
		} finally {
			if (session != null) {
				session.getTransaction().commit();
				session.close();
			}
			logger.info("SendNotifications ended...");
		}
	}

	public static PropertyBean[] getPropertyBeans() {
		return new PropertyBean[] {};
	}

}
