package com.lynxspa.sdm.core.services.quartz.api;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.beans.factory.annotation.Autowired;

import com.lynxspa.entities.application.flow.utils.WorkflowUtils;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.core.managers.configuration.adapters.ConfigurationManagerAdapter;
import com.lynxspa.sdm.core.model.dictionaries.LogErrorDict;
import com.lynxspa.sdm.core.model.dictionaries.states.FlowTASKEXECUTIONStateDict;
import com.lynxspa.sdm.core.model.tasks.TaskExecution;
import com.lynxspa.sdm.core.services.PersistenceService;
import com.lynxspa.sdm.core.services.quartz.api.exceptions.ExecutionException;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.microportal.MicroportalManager;

public abstract class SdmBasicJob implements StatefulJob {

	private @Autowired
	PersistenceService									persistenceService;

	private @Autowired
	ConfigurationManagerAdapter							cfgService;

	private static final Logger							logger				= Logger.getLogger(SdmBasicJob.class);
	private static final Map<Long, TaskExecutionBean>	progressStatus		= new Hashtable<Long, TaskExecutionBean>();

	public static final String							STOPCHAIN			= "stopChain";
	public static final String							LAUNCHISSCHEDULLED	= "isSchedulledLaunch";
	public static final String							LAUNCHUSER			= "launchUser";
	public static final String							LAUNCHLOCALE		= "launchLocale";
	public static final String							LAUNCHPROPERTIES	= "launchProperties";
	public static final String							ISRETRY				= "retry";
	public static final String							PROGRESSSTATUS		= "progressStatus";
	public static final String							PROGRESS			= "progress";

	protected Session									parentSession		= null;

	@SuppressWarnings("unchecked")
	public void execute(final JobExecutionContext _jobExecutionContext) throws JobExecutionException {

		final JobDetail jobDetail = _jobExecutionContext.getJobDetail();
		final JobDataMap jobDataMap = jobDetail.getJobDataMap();
		Set<Entry<String, String>> overwritedProperties = null;

		try {
			parentSession = persistenceService.getHibernateSessionFactory().openSession();
			// Recover execution context params
			final long taskId = Long.valueOf(jobDetail.getName());
			final boolean isSchedulled = (Boolean) jobDataMap.get(SdmBasicJob.LAUNCHISSCHEDULLED);
			final String launchUser = (String) jobDataMap.get(SdmBasicJob.LAUNCHUSER);
			final Locale launchLocale = (Locale) jobDataMap.get(SdmBasicJob.LAUNCHLOCALE);
			final Map<String, String> launchProperties = (Map<String, String>) jobDataMap.get(SdmBasicJob.LAUNCHPROPERTIES);
			// Launch execution
			SdmBasicJob.log("-> START", isSchedulled, taskId, launchUser);
			for (TaskExecutionBean taskExecutionBean : prepareTaskExecution(parentSession, taskId, isSchedulled, launchUser, launchLocale, launchProperties)) {
				SdmBasicJob.log("Execution {3} -> START", isSchedulled, taskId, launchUser, taskExecutionBean.getId());
				try {
					startTaskExecution(parentSession, taskExecutionBean, overwritedProperties);
					HibernateUtils.beguinTransaction(parentSession);
					executeJob(taskExecutionBean);
					if (FlowTASKEXECUTIONStateDict.EXER != taskExecutionBean.getStatus()) {
						taskExecutionBean.setStatus((taskExecutionBean.isDataProcessed()) ? FlowTASKEXECUTIONStateDict.NDOK : FlowTASKEXECUTIONStateDict.NODT);
					} else {
						taskExecutionBean.setStatus(FlowTASKEXECUTIONStateDict.NDER);
					}
					HibernateUtils.commitTransaction(parentSession);
					endTaskExecution(parentSession, taskExecutionBean);
					logger.info("Clear hibernate session cache for enable high performance...");
					// Clear de Hibernate session for enable high performance
					if (parentSession instanceof Session) {
						((Session) parentSession).clear();
					}
				} catch (ExecutionException e) {
					HibernateUtils.rollbackTransaction(parentSession);
					logger.error("Failed process of " + this.getClass() + ".", e);
					failTaskExecution(_jobExecutionContext, parentSession, taskExecutionBean, new FPMException(e));
				} catch (Throwable e) {
					HibernateUtils.rollbackTransaction(parentSession);
					logger.error("Failed process of " + this.getClass() + ".", e);
					final FPMException exception = new FPMException(LogErrorDict.TASKSCHEDULEDEXECUTIONFAIL, new Object[] { jobDetail.getName() }, e);
					failTaskExecution(_jobExecutionContext, parentSession, taskExecutionBean, exception);
				} finally {
					overwritedProperties = taskExecutionBean.getPropertyOverwriteEntries();
				}
				SdmBasicJob.log("Execution {3} -> END", isSchedulled, taskId, launchUser, taskExecutionBean.getId());
				if ((taskExecutionBean.getExecutionVariable(SdmBasicJob.STOPCHAIN) != null) && ((Boolean) taskExecutionBean.getExecutionVariable(SdmBasicJob.STOPCHAIN))) {
					SdmBasicJob.log("Stopping Chain due previous target execution", isSchedulled, taskId, launchUser, taskExecutionBean.getId());
					break;
				}
			}
			SdmBasicJob.log("-> END", isSchedulled, taskId, launchUser);
		} catch (FPMException e) {
			try {
				LogUtils.createLog(parentSession, (String) jobDataMap.get(SdmBasicJob.LAUNCHUSER), MicroportalManager.BUNDLENAME, (Locale) jobDataMap.get(SdmBasicJob.LAUNCHLOCALE), MicroportalManager.getInstance().getApplication(parentSession), e);
			} catch (Throwable e1) {
				e1.printStackTrace();
			}
		} catch (Throwable e) {
			try {
				final FPMException exception = new FPMException(LogErrorDict.TASKSCHEDULEDEXECUTIONFAIL, new Object[] { _jobExecutionContext.getJobDetail().getName() }, e);
				LogUtils.createLog(parentSession, (String) jobDataMap.get(SdmBasicJob.LAUNCHUSER), MicroportalManager.BUNDLENAME, (Locale) jobDataMap.get(SdmBasicJob.LAUNCHLOCALE), MicroportalManager.getInstance().getApplication(parentSession), exception);
			} catch (Throwable e1) {
				e1.printStackTrace();
			}
		} finally {
			if (parentSession != null) {
				parentSession.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected Queue<TaskExecutionBean> prepareTaskExecution(final Object _session, final long _taskId, final boolean _isSchedulled, final String _user, final Locale _locale, final Map<String, String> _launchProperties) throws FPMException {

		Queue<TaskExecutionBean> reply = null;
		Object alternativeSession = null;
		Query query = null;

		try {
			alternativeSession = HibernateUtils.openStatelessSession(_session);
			HibernateUtils.beguinTransaction(alternativeSession);
			final Map<String, String> processProperties = new HashMap<String, String>();
			query = HibernateUtils.createQuery(alternativeSession, "select property.code,property.value from ProcessProperty as property join property.process as proc join proc.tasks as task with task.id=:taskId");
			query.setLong("taskId", _taskId);
			query.setReadOnly(true);
			for (Object[] property : (List<Object[]>) query.list())
				processProperties.put((String) property[0], (String) property[1]);
			if (_launchProperties != null) {
				for (Entry<String, String> entry : _launchProperties.entrySet())
					processProperties.put((String) entry.getKey(), (String) entry.getValue());
			}
			query = HibernateUtils.createQuery(alternativeSession, "select proc.name,proc.classImpl from SystemProcess as proc join proc.tasks as task with task.id=:taskId");
			query.setLong("taskId", _taskId);
			query.setReadOnly(true);
			final Object[] process = (Object[]) query.uniqueResult();
			reply = getRequiredExecutions(alternativeSession, new TaskExecutionBean(_taskId, (String) process[0], AvaliableJob.findAvaliableJobByImplementorClass((String) process[1]).name(), _user, _locale, _isSchedulled, FlowTASKEXECUTIONStateDict.WAIT, processProperties));
			HibernateUtils.commitTransaction(alternativeSession);
		} catch (FPMException e) {
			HibernateUtils.rollbackTransaction(alternativeSession);
			throw e;
		} catch (Exception e) {
			HibernateUtils.rollbackTransaction(alternativeSession);
			throw new FPMException(e);
		} finally {
			HibernateUtils.close(alternativeSession);
		}

		return reply;
	}

	protected void startTaskExecution(final Object _session, final TaskExecutionBean _taskExecutionBean, final Set<Entry<String, String>> _overwritedProperties) throws FPMException {

		Object alternativeSession = null;

		try {
			alternativeSession = HibernateUtils.openStatelessSession(_session);
			HibernateUtils.beguinTransaction(alternativeSession);
			final TaskExecution taskExecution = new TaskExecution(_taskExecutionBean.getTaskId(), _taskExecutionBean.getUser());
			taskExecution.setSchedulled(_taskExecutionBean.isSchedulled());
			taskExecution.setExecutionName(_taskExecutionBean.getExecutionName());
			taskExecution.setProgressStatus(_taskExecutionBean.getProgressStatus());
			taskExecution.setActualReferenceProgress(_taskExecutionBean.getActualReferenceProgress());
			taskExecution.setTotalReferenceProgress(_taskExecutionBean.getTotalReferenceProgress());
			HibernateUtils.customSave(alternativeSession, taskExecution, _taskExecutionBean.getUser());
			_taskExecutionBean.setId(taskExecution.getId());
			SdmBasicJob.progressStatus.put(_taskExecutionBean.getId(), _taskExecutionBean);
			if (_overwritedProperties != null) {
				for (Entry<String, String> propertyOverwrite : _overwritedProperties) {
					_taskExecutionBean.setProperty(propertyOverwrite.getKey(), propertyOverwrite.getValue());
				}
			}
			_taskExecutionBean.setStatus(FlowTASKEXECUTIONStateDict.EXEC);
			WorkflowUtils.changeState(MicroportalManager.getInstance(), _taskExecutionBean.getLocale().getLanguage(), _taskExecutionBean.getUser(), alternativeSession, taskExecution, _taskExecutionBean.getStatus(), false);
			HibernateUtils.commitTransaction(alternativeSession);
		} catch (FPMException e) {
			HibernateUtils.rollbackTransaction(alternativeSession);
			throw e;
		} catch (Exception e) {
			HibernateUtils.rollbackTransaction(alternativeSession);
			throw new FPMException(e);
		} finally {
			HibernateUtils.close(alternativeSession);
		}
	}

	protected void updateStatus(final Object _session, final TaskExecutionBean _taskExecutionBean) throws FPMException {

		Object alternativeSession = null;

		try {
			alternativeSession = HibernateUtils.openStatelessSession(_session);
			HibernateUtils.beguinTransaction(alternativeSession);
			final TaskExecution taskExecution = HibernateUtils.getEntity(alternativeSession, TaskExecution.class, _taskExecutionBean.getId());
			WorkflowUtils.changeState(MicroportalManager.getInstance(), _taskExecutionBean.getLocale().getLanguage(), _taskExecutionBean.getUser(), alternativeSession, taskExecution, _taskExecutionBean.getStatus(), false);
			HibernateUtils.commitTransaction(alternativeSession);
		} catch (FPMException e) {
			HibernateUtils.rollbackTransaction(alternativeSession);
			throw e;
		} catch (Exception e) {
			HibernateUtils.rollbackTransaction(alternativeSession);
			throw new FPMException(e);
		} finally {
			HibernateUtils.close(alternativeSession);
		}
	}

	protected void setRecoveryPoint(final Object _session, final TaskExecutionBean _taskExecutionBean, final String _progresStatus, final long _progress) throws Exception {

		Object alternativeSession = null;

		try {
			alternativeSession = HibernateUtils.openStatelessSession(_session);
			HibernateUtils.beguinTransaction(alternativeSession);
			final Query query = HibernateUtils.createQuery(alternativeSession, "update TaskExecution set executionName=:executionName,progressStatus=:progressStatus,progress=:progress,auditor.updateDate=:updateDate,auditor.updateUser=:updateUser where id=:id");
			query.setString("executionName", _taskExecutionBean.getExecutionName());
			query.setString("progressStatus", _progresStatus);
			query.setLong("progress", _progress);
			query.setLong("id", _taskExecutionBean.getId());
			query.setTimestamp("updateDate", Calendar.getInstance().getTime());
			query.setString("updateUser", _taskExecutionBean.getUser());
			if (query.executeUpdate() == 0)
				throw new FPMException(LogErrorDict.TASKRECOVERYPOINT, new Object[] { _taskExecutionBean.getId(), _progresStatus, _progress });
			HibernateUtils.commitTransaction(alternativeSession);
		} catch (FPMException e) {
			HibernateUtils.rollbackTransaction(alternativeSession);
			throw e;
		} catch (Exception e) {
			HibernateUtils.rollbackTransaction(alternativeSession);
			throw new FPMException(e);
		} finally {
			HibernateUtils.close(alternativeSession);
		}
	}

	protected void endTaskExecution(final Object _session, final TaskExecutionBean _taskExecutionBean) throws FPMException {

		Object alternativeSession = null;

		try {
			SdmBasicJob.progressStatus.remove(_taskExecutionBean.getId());
			alternativeSession = HibernateUtils.openStatelessSession(_session);
			HibernateUtils.beguinTransaction(alternativeSession);
			final Query query = HibernateUtils.createQuery(alternativeSession, "update TaskExecution set actualReferenceProgress=:actualReferenceProgress,totalReferenceProgress=:totalReferenceProgress,auditor.updateDate=:updateDate,auditor.updateUser=:updateUser,progressStatus=:progressStatus where id=:id");
			query.setString("progressStatus", "END");
			query.setLong("actualReferenceProgress", 1l);
			query.setLong("totalReferenceProgress", 1l);
			query.setLong("id", _taskExecutionBean.getId());
			query.setTimestamp("updateDate", Calendar.getInstance().getTime());
			query.setString("updateUser", _taskExecutionBean.getUser());
			query.executeUpdate();
			HibernateUtils.commitTransaction(alternativeSession);
			HibernateUtils.beguinTransaction(alternativeSession);
			final TaskExecution taskExecution = persistenceService.get(TaskExecution.class, _taskExecutionBean.getId());
			WorkflowUtils.changeState(MicroportalManager.getInstance(), _taskExecutionBean.getLocale().getLanguage(), _taskExecutionBean.getUser(), alternativeSession, taskExecution, _taskExecutionBean.getStatus(), false);
			HibernateUtils.commitTransaction(alternativeSession);
		} catch (FPMException e) {
			HibernateUtils.rollbackTransaction(alternativeSession);
			throw e;
		} catch (Exception e) {
			HibernateUtils.rollbackTransaction(alternativeSession);
			throw new FPMException(e);
		} finally {
			HibernateUtils.close(alternativeSession);
		}
	}

	protected void failTaskExecution(final JobExecutionContext _jobExecutionContext, final Object _session, final TaskExecutionBean _taskExecutionBean, final FPMException _exception) throws FPMException {

		final JobDetail jobDetail = _jobExecutionContext.getJobDetail();
		final JobDataMap jobDataMap = jobDetail.getJobDataMap();

		try {
			LogUtils.createLog(_session, (String) jobDataMap.get(SdmBasicJob.LAUNCHUSER), MicroportalManager.BUNDLENAME, (Locale) jobDataMap.get(SdmBasicJob.LAUNCHLOCALE), MicroportalManager.getInstance().getApplication(_session), _exception);
			if (_taskExecutionBean != null) {
				Object alternativeSession = null;
				try {
					SdmBasicJob.progressStatus.remove(_taskExecutionBean.getId());
					alternativeSession = HibernateUtils.openStatelessSession(_session);
					HibernateUtils.beguinTransaction(alternativeSession);
					final Query query = HibernateUtils.createQuery(alternativeSession, "update TaskExecution set actualReferenceProgress=:actualReferenceProgress,totalReferenceProgress=:totalReferenceProgress,auditor.updateDate=:updateDate,auditor.updateUser=:updateUser,progressStatus=:progressStatus where id=:id");
					query.setString("progressStatus", "FAIL");
					query.setLong("actualReferenceProgress", _taskExecutionBean.getActualReferenceProgress());
					query.setLong("totalReferenceProgress", _taskExecutionBean.getTotalReferenceProgress());
					query.setLong("id", _taskExecutionBean.getId());
					query.setTimestamp("updateDate", Calendar.getInstance().getTime());
					query.setString("updateUser", _taskExecutionBean.getUser());
					query.executeUpdate();
					final TaskExecution taskExecution = HibernateUtils.getEntity(alternativeSession, TaskExecution.class, _taskExecutionBean.getId());
					WorkflowUtils.changeState(MicroportalManager.getInstance(), _taskExecutionBean.getLocale().getLanguage(), _taskExecutionBean.getUser(), alternativeSession, taskExecution, FlowTASKEXECUTIONStateDict.FAIL, null, _exception, false);
					HibernateUtils.commitTransaction(alternativeSession);
				} catch (FPMException e) {
					HibernateUtils.rollbackTransaction(alternativeSession);
					throw e;
				} catch (Exception e) {
					HibernateUtils.rollbackTransaction(alternativeSession);
					throw new FPMException(e);
				} finally {
					HibernateUtils.close(alternativeSession);
				}
			} else {
				LogUtils.createLog(_session, String.valueOf(jobDataMap.get(SdmBasicJob.LAUNCHUSER)), MicroportalManager.BUNDLENAME, cfgService.getApplicationLocale(), MicroportalManager.getInstance().getApplication(_session), LogErrorDict.TASKSCHEDULEDEXECUTIONFAIL, new Object[] { jobDetail.getName() }, _exception);
			}
		} catch (FPMException e) {
			SdmBasicJob.log("ERROR", jobDataMap.get(SdmBasicJob.LAUNCHISSCHEDULLED), jobDetail.getName(), jobDataMap.get(SdmBasicJob.LAUNCHUSER), e);
		}
		if (isJobQueueTransactional())
			throw _exception;
	}

	protected Queue<TaskExecutionBean> getRequiredExecutions(final Object _session, final TaskExecutionBean _taskExecutionBean) throws FPMException {

		final Queue<TaskExecutionBean> reply = new LinkedList<TaskExecutionBean>();

		reply.add(_taskExecutionBean);

		return reply;
	}

	protected boolean isJobQueueTransactional() {
		return false;
	}

	protected abstract void executeJob(final TaskExecutionBean _taskExecution) throws ExecutionException;

	public static final TaskExecutionBean getProgressStatus(final Long _taskId) {
		return SdmBasicJob.progressStatus.get(_taskId);
	}

	private static final void log(final String _message, final Object _isSchedulled, final Object _task, final Object _user) {
		SdmBasicJob.log(_message, _isSchedulled, _task, _user, null, null);
	}

	private static final void log(final String _message, final Object _isSchedulled, final Object _task, final Object _user, final Object _execution) {
		SdmBasicJob.log(_message, _isSchedulled, _task, _user, _execution, null);
	}

	private static final void log(final String _message, final Object _isSchedulled, final Object _task, final Object _user, final Object _execution, final Throwable _exception) {
		if (logger.isDebugEnabled()) {
			if (_exception != null) {
				logger.debug(MessageFormat.format("({0}) Job [{1}] {2} execution " + _message, _user, _task, (((_isSchedulled != null) && ((Boolean) _isSchedulled)) ? "schedulled" : "manual"), _execution), _exception);
			} else {
				logger.debug(MessageFormat.format("({0}) Job [{1}] {2} execution " + _message, _user, _task, (((_isSchedulled != null) && ((Boolean) _isSchedulled)) ? "schedulled" : "manual"), _execution));
			}
		}
	}
}
