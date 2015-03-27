package com.lynxspa.sdm.core.services.quartz.jobs.notifications.beans;

import java.sql.Timestamp;

import javapns.devices.Device;

public class KnownDevice implements Device {
	private String		deviceId;
	private Timestamp	lastRegister;
	private String		deviceToken;
	private Long		customerId;
	private Long		notificationInstanceId;

	public KnownDevice(String deviceId, Timestamp lastRegister, String deviceToken, Long customerId, Long notificationInstanceId) {
		this.deviceId = deviceId;
		this.lastRegister = lastRegister;
		this.deviceToken = deviceToken;
		this.customerId = customerId;
		this.notificationInstanceId = notificationInstanceId;
	}

	public KnownDevice() {
	}

	@Override
	public String getDeviceId() {
		return deviceId;
	}

	@Override
	public Timestamp getLastRegister() {
		return lastRegister;
	}

	@Override
	public String getToken() {
		return deviceToken;
	}

	@Override
	public void setDeviceId(String arg0) {
		this.deviceId = arg0;
	}

	@Override
	public void setLastRegister(Timestamp arg0) {
		this.lastRegister = arg0;
	}

	@Override
	public void setToken(String arg0) {
		this.deviceToken = arg0;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getNotificationInstanceId() {
		return notificationInstanceId;
	}

	public void setNotificationInstanceId(Long notificationInstanceId) {
		this.notificationInstanceId = notificationInstanceId;
	}
}
