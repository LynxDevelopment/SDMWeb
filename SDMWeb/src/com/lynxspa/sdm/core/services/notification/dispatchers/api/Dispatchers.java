package com.lynxspa.sdm.core.services.notification.dispatchers.api;

import com.lynxspa.sdm.core.model.notifications.DeviceNotificationInstance;

public enum Dispatchers {
//	IOS_DISPATCHER(IOSNotificationDispatcher.class),
//	ANDROID_DISPATCHER(AndroidNotificationDispatcher.class);
	TEST_DISPATCHER (com.lynxspa.sdm.core.services.notification.dispatchers.api.NotificationDispatcher.class);
	private final Class<? extends NotificationDispatcher>	dispatcherClass;

	private Dispatchers(Class<? extends NotificationDispatcher> dispatcherClass) {
		this.dispatcherClass = dispatcherClass;
	}

	public Class<? extends NotificationDispatcher> getDispatcherClass() {
		return dispatcherClass;
	}

	public static NotificationDispatcher getDispatcherFor(DeviceNotificationInstance instance) {
		try {
			for (Dispatchers d : values()) {
				NotificationDispatcher dispatcher = d.dispatcherClass.newInstance();
				if (dispatcher.supports(instance)) {
					return dispatcher;
				}
			}
		} catch (Throwable e) {
			throw new IllegalStateException(e);
		}
		return null;
	}
}
