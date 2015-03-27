package com.lynxspa.sdm.core.model.tasks;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.lynxspa.entities.UpdatableAdapter;
import com.lynxspa.entities.UpdateAuditor;

@Entity
@Table(name = "GPM_PROCESS_NOTIFICATIONGROUP")
@org.hibernate.annotations.Table(appliesTo = "GPM_PROCESS_NOTIFICATIONGROUP")
public class ProcessNotificationGroup implements UpdatableAdapter {
	private static final long	serialVersionUID	= 6187054714895174928L;
	
	private long						id				= 0l;
	private int							version			= 0;
	private UpdateAuditor				auditor			= null;
	private String						name			= null;
	private String						description		= null;
	private List<ProcessNotification>	notifications	= null;

	public ProcessNotificationGroup() {
		this("DEFAULT", null, null);
	}

	public ProcessNotificationGroup(String user, String name, String description) {
		super();
		this.name = name;
		this.description = description;
		this.auditor = new UpdateAuditor(user);
	}

	@Id
	@SequenceGenerator(name = "NOTIFICATIONGROUP_GEN", sequenceName = "NOTIFICATIONGROUP_GEN", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "NOTIFICATIONGROUP_GEN")
	@Column(name = "ID", nullable = false, updatable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long _id) {
		this.id = _id;
	}

	@Version
	public int getVersion() {
		return this.version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Embedded
	public UpdateAuditor getAuditor() {
		return this.auditor;
	}

	public void setAuditor(UpdateAuditor _auditor) {
		this.auditor = _auditor;
	}

	@Column(name = "NAME", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(targetEntity = ProcessNotification.class, mappedBy = "notificationGroup", fetch = FetchType.LAZY)
	public List<ProcessNotification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<ProcessNotification> notifications) {
		this.notifications = notifications;
	}
}
