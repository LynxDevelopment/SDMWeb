package com.lynxspa.sdm.core.services.quartz.api;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lynxspa.sdm.core.model.dictionaries.states.FlowTASKEXECUTIONStateDict;

public class TaskExecutionBean implements Serializable {

	private static final long					serialVersionUID		= 4462056904316022153L;

	private long								id						= 0l;
	private long								taskId					= 0l;
	private String								executionName			= "";
	private String								process					= null;
	private String								processTypeId			= null;
	private String								user					= null;
	private Locale								locale					= null;
	private boolean								schedulled				= false;
	private Map<String, String>					properties				= null;
	private Map<String, String>					propertiesOverwrite		= null;

	private volatile FlowTASKEXECUTIONStateDict	status					= null;
	private volatile String						progressStatus			= "PREPARING";
	private volatile Long						actualReferenceProgress	= 0l;
	private volatile Long						totalReferenceProgress	= 1l;
	private volatile boolean					dataProcessed			= false;

	private Map<String, Object>					executionVariables		= new HashMap<String, Object>(10);

	public TaskExecutionBean(final TaskExecutionBean _taskExecutionBean, final String _executionName) {
		this(_taskExecutionBean.taskId, _taskExecutionBean.process, _taskExecutionBean.processTypeId, _taskExecutionBean.user, _taskExecutionBean.locale, _taskExecutionBean.schedulled, _taskExecutionBean.status, _taskExecutionBean.properties);
		this.setProgressStatus(new String(_taskExecutionBean.progressStatus));
		this.setActualReferenceProgress(new Long(_taskExecutionBean.actualReferenceProgress));
		this.setTotalReferenceProgress(new Long(_taskExecutionBean.totalReferenceProgress));
		this.executionVariables = new HashMap<String, Object>(_taskExecutionBean.executionVariables);
		this.executionName = _executionName;

	}

	public TaskExecutionBean(final long _taskId, final String _process, final String _processTypeId, final String _user, final Locale _locale, final boolean _schedulled, final FlowTASKEXECUTIONStateDict _status, final Map<String, String> _properties) {
		this.taskId = _taskId;
		this.process = _process;
		this.processTypeId = _processTypeId;
		this.user = _user;
		this.locale = _locale;
		this.schedulled = _schedulled;
		this.status = _status;
		this.properties = (_properties != null) ? _properties : new HashMap<String, String>(0);
		this.propertiesOverwrite = new HashMap<String, String>(0);
	}

	public long getId() {
		return id;
	}

	public void setId(final long _id) {
		this.id = _id;
	}

	public String getExecutionName() {
		return executionName;
	}

	public void setExecutionName(String executionName) {
		this.executionName = executionName;
	}

	public long getTaskId() {
		return taskId;
	}

	public String getProcess() {
		return process;
	}

	public String getProcessTypeId() {
		return processTypeId;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return user;
	}

	public Locale getLocale() {
		return locale;
	}

	public boolean isSchedulled() {
		return schedulled;
	}

	public String getProperty(final String _property) {
		return properties.get(_property);
	}

	public String getProperty(final PropertyBean _property) {
		return properties.get(_property.toString());
	}

	public void setProperty(final String _property, final String _value) {
		this.properties.put(_property, _value);
	}

	public void setProperty(final PropertyBean _property, final String _value) {
		this.properties.put(_property.toString(), _value);
	}

	public void setPropertyOverwrite(final String _property, final String _value) {
		this.propertiesOverwrite.put(_property, _value);
	}

	public void setPropertyOverwrite(final PropertyBean _property, final String _value) {
		this.propertiesOverwrite.put(_property.toString(), _value);
	}

	public String getPropertyOverwrite(final PropertyBean _property) {
		return this.propertiesOverwrite.get(_property);
	}

	public Set<Entry<String, String>> getPropertyOverwriteEntries() {
		return this.propertiesOverwrite.entrySet();
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public String getProgressStatus() {
		return progressStatus;
	}

	public void setProgressStatus(String progressStatus) {
		this.progressStatus = progressStatus;
	}

	public Long getActualReferenceProgress() {
		return actualReferenceProgress;
	}

	public void setActualReferenceProgress(Long actualReferenceProgress) {
		this.actualReferenceProgress = actualReferenceProgress;
	}

	public FlowTASKEXECUTIONStateDict getStatus() {
		return status;
	}

	public void setStatus(FlowTASKEXECUTIONStateDict _status) {
		this.status = _status;
	}

	public Long getTotalReferenceProgress() {
		return totalReferenceProgress;
	}

	public void setTotalReferenceProgress(Long totalReferenceProgress) {
		this.totalReferenceProgress = totalReferenceProgress;
	}

	public boolean isDataProcessed() {
		return dataProcessed;
	}

	public void setDataProcessed(boolean dataProcessed) {
		this.dataProcessed = dataProcessed;
	}

	public Object getExecutionVariable(final PropertyBean _variable) {
		return executionVariables.get(_variable.toString());
	}

	public Object getExecutionVariable(final String _variable) {
		return executionVariables.get(_variable);
	}

	public void setExecutionVariable(final PropertyBean _variable, final Object _value) {
		this.executionVariables.put(_variable.toString(), _value);
	}

	public void setExecutionVariable(final String _variable, final Object _value) {
		this.executionVariables.put(_variable, _value);
	}
}