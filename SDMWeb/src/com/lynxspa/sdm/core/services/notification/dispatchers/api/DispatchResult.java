package com.lynxspa.sdm.core.services.notification.dispatchers.api;

import com.lynxspa.sdm.core.model.notifications.DeviceNotificationInstance;

public class DispatchResult {
	private boolean						successful;
	private DeviceNotificationInstance	notificationInstance;
	private Exception					error;

	public DispatchResult(boolean successful, DeviceNotificationInstance notificationInstance, Exception error) {
		this.successful = successful;
		this.notificationInstance = notificationInstance;
		this.error = error;
	}

	public boolean isSuccessful() {
		return successful;
	}

	public DeviceNotificationInstance getNotificationInstance() {
		return notificationInstance;
	}

	public Exception getError() {
		return error;
	}
}
