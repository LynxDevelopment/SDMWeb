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
import org.hibernate.annotations.Index;

import com.lynxspa.entities.UpdatableAdapter;
import com.lynxspa.entities.UpdateAuditor;
import com.lynxspa.entities.application.flow.Flow;
import com.lynxspa.entities.application.flow.State;
import com.lynxspa.entities.application.flow.operations.OperableAdapter;
import com.lynxspa.entities.application.flow.operations.OperationStatus;
import com.lynxspa.sdm.core.model.dictionaries.WorkflowDict;
import com.lynxspa.sdm.core.model.dictionaries.states.FlowTASKEXECUTIONStateDict;

@Entity
@Table(name = "SDM_TASKEXECUTIONS")
@org.hibernate.annotations.Table(appliesTo = "SDM_TASKEXECUTIONS", indexes = { @Index(name = "IDX_TASKEXECUTION_TASK", columnNames = "FKTASK"), @Index(name = "IDX_TASKEXECUTION_VERSION", columnNames = "VERSION"), @Index(name = "IDX_TASKEXECUTION_STATUS", columnNames = { "ISENDED", "FKSTATEFLOW", "FKSTATE" }) })
public class TaskExecution implements UpdatableAdapter, OperableAdapter {

	private static final long	serialVersionUID		= 689161141548463435L;

	private long				id;

	private String				executionName			= "";
	private String				progressStatus			= "";
	private long				progress				= 0l;
	private boolean				isSchedulled			= false;
	private long				actualReferenceProgress	= 0l;
	private long				totalReferenceProgress	= 1l;
	private SystemTask			task					= null;

	private int					version;
	private UpdateAuditor		auditor;
	private OperationStatus		operationStatus;

	public TaskExecution() {
		this((SystemTask) null, null);
	}

	public TaskExecution(final String _user) {
		this((SystemTask) null, _user);
	}

	public TaskExecution(final long _task, final String _user) {
		this(new SystemTask(_task), _user);
	}

	public TaskExecution(final SystemTask _task, final String _user) {
		super();
		this.task = _task;
		this.auditor = new UpdateAuditor(_user);
		this.operationStatus = new OperationStatus(new State(new Flow(WorkflowDict.TASKEXECUTION.getId(), null, null, null), FlowTASKEXECUTIONStateDict.WAIT.getId(), null, null));
	}

	@Id
	@SequenceGenerator(name = "TASKEXECUTION_GEN", sequenceName = "TASKEXECUTION_GEN", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TASKEXECUTION_GEN")
	@Column(name = "ID", nullable = false, updatable = false)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "EXECUTIONNAME", nullable = true, length = 512)
	public String getExecutionName() {
		return executionName;
	}

	public void setExecutionName(String executionName) {
		this.executionName = executionName;
	}

	@Column(name = "PROGRESSSTATUS", nullable = true, length = 4000)
	public String getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(String progressStatus) {
		this.progressStatus = progressStatus;
	}

	@Column(name = "ACTUALREFERENCEPROGRESS", nullable = true, length = 40)
	public long getActualReferenceProgress() {
		return actualReferenceProgress;
	}

	public void setActualReferenceProgress(long actualReferenceProgress) {
		this.actualReferenceProgress = actualReferenceProgress;
	}

	@Column(name = "TOTALREFERENCEPROGRESS", nullable = true, length = 40)
	public long getTotalReferenceProgress() {
		return totalReferenceProgress;
	}

	public void setTotalReferenceProgress(long totalReferenceProgress) {
		this.totalReferenceProgress = totalReferenceProgress;
	}

	@Column(name = "PROGRESS", nullable = true)
	public long getProgress() {
		return progress;
	}

	public void setProgress(long progress) {
		this.progress = progress;
	}

	@ManyToOne(targetEntity = SystemTask.class, fetch = FetchType.LAZY)
	@ForeignKey(name = "FK_EXECUTION_TASK")
	@JoinColumn(name = "FKTASK")
	public SystemTask getTask() {
		return task;
	}

	public void setTask(SystemTask task) {
		this.task = task;
	}

	@Column(name = "ISSCHEDULLED", nullable = false)
	public boolean isSchedulled() {
		return isSchedulled;
	}

	public void setSchedulled(boolean isSchedulled) {
		this.isSchedulled = isSchedulled;
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
}
