package com.lynxspa.sdm.core.model.notifications;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "GPM_NOTIFICATIONS")
public class Notification {
	private Long								id;
	private Date								scheduledDate;
	private String								text;
	private String								category;
	//private Customer							customer;

	private List<DeviceNotificationInstance>	notificationInstances;

	@Id
	@SequenceGenerator(name = "NOTIFICATION_GEN", sequenceName = "NOTIFICATION_GEN", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "NOTIFICATION_GEN")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SCHEDULEDDATE")
	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	@Column(name = "TEXT", length = 160)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "CATEGORY", length = 16)
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "FKCUSTOMER")
//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="notification")
	public List<DeviceNotificationInstance> getNotificationInstances() {
		return notificationInstances;
	}

	public void setNotificationInstances(List<DeviceNotificationInstance> notificationInstances) {
		this.notificationInstances = notificationInstances;
	}

}
