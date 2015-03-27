package com.lynxspa.sdm.core.services.quartz.jobs.notifications.beans;

import java.io.Serializable;

public final class NotificationRefBean implements Serializable {
	private static final long	serialVersionUID	= 611187992740306220L;

	private Long				instanceId;
	private String				deviceIdentifier;
	private String				notificationCategory;
	private String				notificationText;

	public NotificationRefBean(Long instanceId, String deviceIdentifier, String notificationCategory, String notificationText) {
		this.instanceId = instanceId;
		this.deviceIdentifier = deviceIdentifier;
		this.notificationCategory = notificationCategory;
		this.notificationText = notificationText;
	}

	public Long getInstanceId() {
		return instanceId;
	}

	public String getDeviceIdentifier() {
		return deviceIdentifier;
	}

	public String getNotificationCategory() {
		return notificationCategory;
	}

	public String getNotificationText() {
		return notificationText;
	}

	@Override
	public String toString() {
		return String.format("NotificationRefBean [instanceId=%s, deviceIdentifier=%s, notificationCategory=%s, notificationText=%s]", instanceId, deviceIdentifier, notificationCategory, notificationText);
	}
}
