package com.lynxspa.sdm.core.services.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lynxspa.sdm.core.model.notifications.DeviceNotificationInstance;
import com.lynxspa.sdm.core.model.notifications.Notification;
import com.lynxspa.sdm.core.services.PersistenceService;
import com.lynxspa.sdm.core.services.notification.dispatchers.api.Dispatchers;
import com.lynxspa.sdm.core.services.notification.dispatchers.api.NotificationDispatcher;
import com.lynxspa.sdm.core.services.utils.SpringContextService;
//import com.lynxspa.sdm.core.model.customers.Customer;

@Service
public class NotificationService {

	@Autowired
	private PersistenceService		persistenceService;

	@Autowired
	private SpringContextService	springContextService;

	private final Integer			FETCH_SIZE	= 2000;

	/**
	 * Planifica una notificación push para enviar por el proceso 
	 * quartz de envio de notificaciones.
	 * 
	 * @param text
	 * @param category
	 * @param customerId
	 * @return La instancia de notificación registrada. 
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Notification createNotification(String text, String category, Long customerId) throws IllegalArgumentException {
//		Customer customer = customerService.getCustomerById(customerId);
//		if (customer == null) {
//			throw new IllegalArgumentException("There isn't any customer with ID( " + customerId + ")");
//		}
//
		Notification notification = new Notification();
//		notification.setCategory(category);
//		notification.setText(text);
//		notification.setScheduledDate(TimeUtils.Now());
//		notification.setCustomer(customer);
//
//		persistenceService.save(notification);
//
//		//@(format off) now it exists, create instances for each device
//		Query q = persistenceService.createQuery(
//		"select Token.device from CustomerDeviceProperty as Token " +
//		"where Token.name = 'PushNotificationToken' " +
//		"and	 Token.customer.id = :customerId "
//		);
//		//@(format on)
//
//		q.setParameter("customerId", customerId);
//		q.setFetchSize(FETCH_SIZE);
//
//		Iterator<Device> devices = q.iterate();
//		while (devices.hasNext()) {
//			Device device = devices.next();
//
//			DeviceNotificationInstance instance = new DeviceNotificationInstance();
//			instance.setDevice(device);
//			instance.setNotification(notification);
//			instance.setSent(false);
//
//			persistenceService.save(instance);
//		}
//
		return persistenceService.get(Notification.class, notification.getId());
	}

	/**
	 * Devuelve un dispatcher para esa notificación.
	 * 
	 * @param instance
	 * @return
	 */
	public NotificationDispatcher getDispatcherForNotificationIntance(DeviceNotificationInstance instance) {
		NotificationDispatcher dispatcher = Dispatchers.getDispatcherFor(instance);
		if (dispatcher != null) {
			springContextService.autowire(dispatcher);
		}
		return dispatcher;
	}
}
