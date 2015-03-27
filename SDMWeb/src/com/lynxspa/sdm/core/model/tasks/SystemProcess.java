package com.lynxspa.sdm.core.model.tasks;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.Index;

import com.lynxspa.entities.UpdatableAdapter;
import com.lynxspa.entities.UpdateAuditor;

@Entity
@Table(name = "SDM_PROCESSES", uniqueConstraints = { @UniqueConstraint(columnNames = { "PROCESSCLASS", "NAME", "ISDELETED" }) })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PROCESSCLASS", discriminatorType = DiscriminatorType.CHAR, length = 1)
@DiscriminatorValue("S")
@org.hibernate.annotations.Table(appliesTo = "SDM_PROCESSES", indexes = { @Index(name = "IDX_PROCESS_CLASS", columnNames = "PROCESSCLASS") })
public class SystemProcess implements UpdatableAdapter {

	private static final long		serialVersionUID	= 444256566419956046L;

	private long					id					= 0l;

	private String					name				= null;
	private String					classImpl			= null;
	private String					type				= null;
	private String					description			= null;

	private int						version				= 0;
	private UpdateAuditor			auditor				= null;

	private List<ProcessProperty>	processProperties	= null;
	private List<SystemTask>		tasks				= null;

	public SystemProcess() {
		this(null);
	}

	public SystemProcess(String user) {
		super();
		this.auditor = new UpdateAuditor(user);
	}

	@Id
	@SequenceGenerator(name = "PROCESS_GEN", sequenceName = "PROCESS_GEN", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PROCESS_GEN")
	@Column(name = "ID", nullable = false, updatable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "NAME", nullable = false, length = 128)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CLASSIMPL", nullable = false, length = 512)
	public String getClassImpl() {
		return classImpl;
	}

	public void setClassImpl(String classImpl) {
		this.classImpl = classImpl;
	}

	@Column(name = "DESCRIPTION", nullable = false, length = 256)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "TYPE", nullable = false, length = 40)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	@OneToMany(cascade = CascadeType.ALL, targetEntity = ProcessProperty.class, fetch = FetchType.LAZY, mappedBy = "process")
	public List<ProcessProperty> getProcessProperties() {
		return processProperties;
	}

	public void setProcessProperties(List<ProcessProperty> processProperties) {
		this.processProperties = processProperties;
	}

	@OneToMany(cascade = CascadeType.ALL, targetEntity = SystemTask.class, fetch = FetchType.LAZY, mappedBy = "process")
	public List<SystemTask> getTasks() {
		return tasks;
	}

	public void setTasks(List<SystemTask> tasks) {
		this.tasks = tasks;
	}
}
