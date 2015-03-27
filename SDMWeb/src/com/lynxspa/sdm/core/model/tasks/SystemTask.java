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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;

import com.lynxspa.entities.UpdatableAdapter;
import com.lynxspa.entities.UpdateAuditor;
import com.lynxspa.entities.application.flow.Flow;
import com.lynxspa.entities.application.flow.State;
import com.lynxspa.entities.application.flow.operations.OperableAdapter;
import com.lynxspa.entities.application.flow.operations.OperationStatus;
import com.lynxspa.sdm.core.model.dictionaries.WorkflowDict;
import com.lynxspa.sdm.core.model.dictionaries.states.FlowTASKStateDict;

@Entity
@Table(name = "SDM_TASKS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TASKCLASS", discriminatorType = DiscriminatorType.CHAR, length = 1)
@DiscriminatorValue("S")
@org.hibernate.annotations.Table(appliesTo = "SDM_TASKS", indexes = { @Index(name = "IDX_TASK_CLASS", columnNames = "TASKCLASS"), @Index(name = "IDX_TASK_PROCESS", columnNames = "FKPROCESS"), @Index(name = "IDX_TASK_VERSION", columnNames = "VERSION"), @Index(name = "IDX_TASK_STATUS", columnNames = { "ISENDED", "FKSTATEFLOW", "FKSTATE" }) })
public class SystemTask implements UpdatableAdapter, OperableAdapter {

	private static final long	serialVersionUID	= -4932627509463937110L;

	private long				id					= 0l;

	private SystemProcess		process				= null;

	private int					version				= 0;
	private UpdateAuditor		auditor				= null;
	private OperationStatus		operationStatus		= null;

	private List<TaskExecution>	tasksExecution		= null;

	public SystemTask() {
		this(null);
	}

	public SystemTask(long _task) {
		this(null);
		this.id = _task;
	}

	public SystemTask(String user) {
		this.auditor = new UpdateAuditor(user);
		this.operationStatus = new OperationStatus(new State(new Flow(WorkflowDict.TASK.getId(), null, null, null), FlowTASKStateDict.NORMAL.getId(), null, null), false);
	}

	@Id
	@SequenceGenerator(name = "TASK_GEN", sequenceName = "TASK_GEN", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TASK_GEN")
	@Column(name = "ID", nullable = false, updatable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(targetEntity = SystemProcess.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_TASK_PROCESS")
	@JoinColumn(name = "FKPROCESS")
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

	@Embedded
	public OperationStatus getOperationStatus() {
		return this.operationStatus;
	}

	public void setOperationStatus(OperationStatus _status) {
		this.operationStatus = _status;
	}

	@OneToMany(cascade = CascadeType.ALL, targetEntity = TaskExecution.class, fetch = FetchType.LAZY, mappedBy = "task")
	public List<TaskExecution> getTasksExecution() {
		return tasksExecution;
	}

	public void setTasksExecution(List<TaskExecution> tasksExecution) {
		this.tasksExecution = tasksExecution;
	}
}
