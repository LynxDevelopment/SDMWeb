package com.lynxspa.sdm.core.model.tasks;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;

import com.lynxit.xweb.users.hibernateentities.XWebUser;
import com.lynxspa.entities.UpdatableAdapter;
import com.lynxspa.entities.UpdateAuditor;
@Entity
@Table(name="GPM_PROCESS_NOTIFICATIONS") 
@org.hibernate.annotations.Table(appliesTo="GPM_PROCESS_NOTIFICATIONS")
public class ProcessNotification implements UpdatableAdapter {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5807887993308191198L;
	private long id=0l;
	private int version=0;
	private UpdateAuditor auditor=null;
	private ProcessNotificationGroup notificationGroup=null;
	private String notifier=null;
	private XWebUser user;
	
	public ProcessNotification (){
		this("DEFAULT");
	}
	public ProcessNotification(String user) {
		super();
		this.auditor=new UpdateAuditor(user);
	}
	
	@Id
	@SequenceGenerator(name="NOTIFICATION_GEN", sequenceName="NOTIFICATION_GEN", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="NOTIFICATION_GEN")
	@Column(name="ID", nullable=false, updatable=false)
	public long getId() {
		return this.id;
	}
	public void setId(long _id) {
		this.id=_id;
	}
	
	@Version
	public int getVersion() {
		return this.version;
	}
	public void setVersion(int version) {
		this.version=version;
	}

	@Embedded
	public UpdateAuditor getAuditor() {
		return this.auditor;
	}
	public void setAuditor(UpdateAuditor _auditor) {
		this.auditor=_auditor;
	}
	
	@ManyToOne(targetEntity = ProcessNotificationGroup.class,fetch=FetchType.LAZY)
	@ForeignKey(name="FK_NOTIFICATION_GROUP")
    @JoinColumn(name="FKNOTIFICATIONGROUP",nullable=false)
	public ProcessNotificationGroup getNotificationGroup() {
		return notificationGroup;
	}
	public void setNotificationGroup(ProcessNotificationGroup notificationGroup) {
		this.notificationGroup = notificationGroup;
	}
	
	@Column(name="NOTIFIER", nullable=true)
	public String getNotifier() {
		return notifier;
	}
	public void setNotifier(String notifier) {
		this.notifier = notifier;
	}
	
	@ManyToOne(targetEntity = XWebUser.class,fetch=FetchType.EAGER)
	@ForeignKey(name="FK_NOTIFICATION_USER")
	@JoinColumn(name="FKNOTIFICATIONUSER",nullable=true)
	public XWebUser getUser() {
		return user;
	}
	public void setUser(XWebUser user) {
		this.user = user;
	}
}
