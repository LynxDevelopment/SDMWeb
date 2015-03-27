package com.lynxspa.sdm.core.services.quartz.jobs.notifications;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.lynxspa.sdm.core.services.PersistenceService;
import com.lynxspa.sdm.core.services.quartz.api.SdmBasicJob;
import com.lynxspa.sdm.core.services.quartz.api.PropertyBean;
import com.lynxspa.sdm.core.services.quartz.api.TaskExecutionBean;
import com.lynxspa.sdm.core.services.quartz.api.exceptions.ExecutionException;
import com.lynxspa.sdm.core.utils.TimeUtils;
import com.lynxspa.validation.utils.ValidationsDict;

public class ClearSentNotifications extends SdmBasicJob {
	@Autowired
	private PersistenceService			persistenceService;

	private static Logger				logger			= Logger.getLogger(ClearSentNotifications.class);

	private static final PropertyBean	DISTANCE_DAYS	= new PropertyBean("distanceDays", ValidationsDict.ISNOTEMPTY, ValidationsDict.ISINTEGER);

	private void initializeExecutionVariables(TaskExecutionBean state) {
		for (PropertyBean p : getPropertyBeans()) {
			state.setExecutionVariable(p, state.getProperty(p));
		}
	}

	@Override
	protected void executeJob(TaskExecutionBean state) throws ExecutionException {
		logger.info("ClearSentNotifications started... ");
		initializeExecutionVariables(state);

		int distanceDays = Integer.valueOf(state.getExecutionVariable(DISTANCE_DAYS).toString());
		Session session = null;

		try {
			session = persistenceService.openSession();
			session.beginTransaction();

			//@(format off)
			Query q = session.createQuery(
			"delete from DeviceNotificationInstance" +
			"	where sent = :true" +
			"	and   sentDate < :sentDate" 
			);
			//@(format on)

			Calendar c = Calendar.getInstance();
			c.add(Calendar.DAY_OF_YEAR, -distanceDays);

			Date sentDate = TimeUtils.asStartOfDay(c.getTime());

			q.setParameter("true", true);
			q.setParameter("sentDate", sentDate);

			int deletedCount = q.executeUpdate();
			
			//@(format off)
			q = session.createQuery(
			"delete from DeviceNotificationInstance instance " +
			"	where instance.notification.scheduledDate < :sentDate"
			);
			//@(format on)
			
			q.setParameter("sentDate", sentDate);
			
			deletedCount += q.executeUpdate();
			
			logger.info("[OK] Removed " + deletedCount + " sent notifications.");
		} finally {
			if (session != null) {
				session.getTransaction().commit();
				session.close();
			}
			logger.info("ClearSentNotifications ended...");
		}

	}

	public static PropertyBean[] getPropertyBeans() {
		return new PropertyBean[] { DISTANCE_DAYS };
	}

}
