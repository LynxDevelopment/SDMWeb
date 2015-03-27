package com.lynxspa.sdm.core.services.notification.dispatchers.api;

import org.hibernate.Session;

import com.lynxspa.sdm.core.model.notifications.DeviceNotificationInstance;

/**
 * Es un delegado que se ocupa de enviar notificaciones push.
 * 
 * @author kevin.mas
 */
public interface NotificationDispatcher {
	/**
	 * Devuelve true si soporta enviar la notificación instance
	 * al dispositivo.
	 * 
	 * @param instance
	 */
	boolean supports(DeviceNotificationInstance instance);

	/**
	 * Envia la notificación al dispositivo y la marca como enviado en BBDD.
	 * 
	 * @param session
	 * @param instance
	 */
	public void dispatch(Session session, DeviceNotificationInstance instance);
}
