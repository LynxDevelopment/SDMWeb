package com.lynxspa.sdm.core.model.notifications;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.lynxspa.sdm.core.model.devices.Device;

@Entity
@Table(name = "GPM_DEVICE_NOTIFICATIONS")
public class DeviceNotificationInstance {
	private Long			id;
	private Device			device;
	private Notification	notification;
	private boolean			sent;
	private Date			sentDate;

	@Id
	@SequenceGenerator(name = "DEVICE_NOTIFICATION_GEN", sequenceName = "DEVICE_NOTIFICATION_GEN", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "DEVICE_NOTIFICATION_GEN")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "FKDEVICE")
	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "FKNOTIFICATION")
	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	@Column(name = "ISSENT")
	public boolean isSent() {
		return sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SENTDATE")
	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
}
