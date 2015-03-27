package com.lynxspa.sdm.core.model.tasks;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;

import com.lynxspa.entities.UpdatableAdapter;
import com.lynxspa.entities.UpdateAuditor;

@Entity
@Table(name = "SDM_PROCESSPROPERTIES")
public class ProcessProperty implements UpdatableAdapter {

	private static final long	serialVersionUID	= 4185633714873473410L;

	private long				id;
	private String				code;
	private String				value;
	private SystemProcess		process;
	private int					version;
	private UpdateAuditor		auditor;

	public ProcessProperty() {
		super();
		this.auditor = new UpdateAuditor();
	}

	public ProcessProperty(String user) {
		super();
		this.auditor = new UpdateAuditor(user);
	}

	@Id
	@SequenceGenerator(name = "PROCESSPROPERTY_GEN", sequenceName = "PROCESSPROPERTY_GEN", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PROCESSPROPERTY_GEN")
	@Column(name = "ID", nullable = false, updatable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "CODE", nullable = false, length = 250)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Lob
	@Column(name = "VALUE", nullable = true)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@ManyToOne(targetEntity = SystemProcess.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_PROCESSPROPERTY_PROCESS")
	@JoinColumn(name = "FKPROCESS")
	@Index(name = "IDX_PROCESSPROPERTY_PROCESS", columnNames = "FKPROCESS")
	public SystemProcess getProcess() {
		return process;
	}

	public void setProcess(SystemProcess process) {
		this.process = process;
	}

	@Version
	@Column(name = "VERSION", nullable = false)
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Embedded
	public UpdateAuditor getAuditor() {
		return auditor;
	}

	public void setAuditor(UpdateAuditor auditor) {
		this.auditor = auditor;
	}
}
