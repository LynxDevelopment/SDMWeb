package com.lynxspa.sdm.core.services.quartz;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.lynxit.xweb.users.LoggedUser;
import com.lynxit.xweb.users.hibernateentities.XWebUser;
import com.lynxspa.entities.application.logs.utils.LogUtils;
import com.lynxspa.exception.FPMException;
import com.lynxspa.sdm.core.managers.configuration.adapters.ConfigurationManagerAdapter;
import com.lynxspa.sdm.core.model.dictionaries.LogErrorDict;
import com.lynxspa.sdm.core.model.dictionaries.LogInfoDict;
import com.lynxspa.sdm.core.model.tasks.SystemTask;
import com.lynxspa.sdm.core.model.tasks.TaskExecution;
import com.lynxspa.sdm.core.model.tasks.UserTask;
import com.lynxspa.sdm.core.services.PersistenceService;
import com.lynxspa.sdm.core.services.quartz.api.SdmBasicJob;
import com.lynxspa.hbt.utils.HibernateUtils;
import com.lynxspa.microportal.MicroportalManager;

@Service
@Scope("singleton")
public class QuartzService {
	@Autowired
	private ConfigurationManagerAdapter	xwebCfgService;

	@Autowired
	private SchedulerFactoryBean		quartzScheduler;

	@Autowired
	private PersistenceService			persistenceService;

	private static final Logger			logger	= Logger.getLogger(QuartzService.class);

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void initialize() {
		logger.info("Initializing Quartz...");
		System.out.println("Initializing Quartz...");
		Session session = persistenceService.getHibernateSessionFactory().openSession();
		System.out.println("Initializing Quartz..."+(session.isConnected()?"Connected":"Disconnected"));
		try {
			System.out.println("cancelRunningOperations");
			cancelRunningOperations(session);
			System.out.println("canceled");
			XWebUser user = null;
			for (UserTask task : (List<UserTask>) HibernateUtils.createQuery(session, "from UserTask where auditor.deleted=false and operationStatus.state.id.code='NORMAL'").list()) {
				user = HibernateUtils.getEntity(session, XWebUser.class, task.getAuditor().getUpdateUser());
				if (user == null) {
					user = HibernateUtils.getEntity(session, XWebUser.class, "administrator");
				}
				System.out.println("user:"+user.getId());
				registerJob(session, task, user, true, null);
				System.out.println("jobregistered");
			}
		} catch (FPMException e) {
			LogUtils.createLog(session, "administrator", MicroportalManager.BUNDLENAME, xwebCfgService.getApplicationLocale(), MicroportalManager.getInstance().getApplication(persistenceService.getHibernateSession()), e);
		} catch (Exception e) {
			final FPMException newException = new FPMException(e);
			LogUtils.createLog(session, "administrator", MicroportalManager.BUNDLENAME, xwebCfgService.getApplicationLocale(), MicroportalManager.getInstance().getApplication(persistenceService.getHibernateSession()), newException, true);
		} finally {
			session.close();
		}
	}

	protected void cancelRunningOperations(Session _session) throws FPMException {
		StatelessSession statelessSession = null;
		try {
			statelessSession = HibernateUtils.openStatelessSession(_session);
			HibernateUtils.beguinTransaction(statelessSession);
			Query query = HibernateUtils.createQuery(statelessSession, "update TaskExecution taskExecution set operationStatus.state.id.code='CANC' where operationStatus.state.id.code in ('WAIT','EXEC','EXER')");
			query.executeUpdate();
			HibernateUtils.commitTransaction(statelessSession);
		} catch (Exception e) {
			HibernateUtils.rollbackTransaction(statelessSession);
			throw new FPMException(e);
		} finally {
			HibernateUtils.close(statelessSession);
		}
	}

	public void terminate(final ServletContext servletContext) throws Exception {

		try {
			if (this.quartzScheduler.getScheduler() != null)
				this.quartzScheduler.getScheduler().shutdown();
		} catch (Exception e) {
			throw new Exception("Quartz Scheduler failed to shutdown cleanly: " + e.toString(), e);
		}
	}

	public int getTriggerStatus(final Session _session, final UserTask _task) throws FPMException {

		int status = 5; // --> unknown status

		try {
			status = this.quartzScheduler.getScheduler().getTriggerState(String.valueOf(_task.getId()), Scheduler.DEFAULT_GROUP);
		} catch (Exception e) {
			status = 5;// if error the set status to unknown
		}

		return status;
	}

	public void resumeTask(final Session _session, final UserTask _task, final LoggedUser _user) throws FPMException {

		try {
			JobDetail jobDetail = this.quartzScheduler.getScheduler().getJobDetail(String.valueOf(_task.getId()), Scheduler.DEFAULT_GROUP);
			if (jobDetail == null) {
				insertOrUpdateTask(_session, _task, _user);
			} else {
				this.quartzScheduler.getScheduler().resumeJob(String.valueOf(_task.getId()), Scheduler.DEFAULT_GROUP);
			}
		} catch (SchedulerException e) {
			final FPMException newException = new FPMException(LogErrorDict.QUARTZ_ERROR_SCHEDULER, new Object[] { e.getMessage() }, e);
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), newException);
			throw newException;
		} catch (Exception e) {
			final FPMException newException = new FPMException(e);
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), newException, true);
			throw newException;
		}
	}

	public void pauseTask(final Session _session, final UserTask _task, final LoggedUser _user) throws FPMException {

		try {
			this.quartzScheduler.getScheduler().pauseJob(String.valueOf(_task.getId()), Scheduler.DEFAULT_GROUP);
		} catch (SchedulerException e) {
			final FPMException newException = new FPMException(LogErrorDict.QUARTZ_ERROR_SCHEDULER, new Object[] { e.getMessage() }, e);
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), newException);
			throw newException;
		} catch (Exception e) {
			final FPMException newException = new FPMException(e);
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), newException, true);
			throw newException;
		}
	}

	public void deleteTask(final Session _session, final UserTask _task, final LoggedUser _user) throws FPMException {

		try {
			HibernateUtils.customDelete(_session, _task, _user.getId());
			this.quartzScheduler.getScheduler().deleteJob(String.valueOf(_task.getId()), Scheduler.DEFAULT_GROUP);
		} catch (SchedulerException e) {
			final FPMException newException = new FPMException(LogErrorDict.QUARTZ_ERROR_SCHEDULER, new Object[] { e.getMessage() }, e);
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), newException);
			throw newException;
		} catch (Exception e) {
			final FPMException newException = new FPMException(e);
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), newException, true);
			throw newException;
		}
	}

	public void insertOrUpdateTask(final Session _session, final UserTask _task, final LoggedUser _user) throws FPMException {

		try {
			HibernateUtils.customSaveOrUpdate(_session, _task, _user.getId());
			registerJob(_session, _task, _user, true, null);
		} catch (FPMException e) {
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), e);
			throw e;
		} catch (Exception e) {
			final FPMException newException = new FPMException(e);
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), newException, true);
			throw newException;
		}
	}

	public void retryExecution(final Session _session, final TaskExecution _task, final LoggedUser _user, final Map<String, String> _properties) throws FPMException {

		final Map<String, String> overwriteProperties = (_properties != null) ? _properties : new HashMap<String, String>();

		overwriteProperties.put(SdmBasicJob.ISRETRY, "true");
		overwriteProperties.put(SdmBasicJob.PROGRESSSTATUS, _task.getProgressStatus());
		overwriteProperties.put(SdmBasicJob.PROGRESS, String.valueOf(_task.getProgress()));
		executeManualTasks(_session, _task.getTask(), _user, _properties);
	}

	public void executeManualTasks(final Session _session, final SystemTask _task, final LoggedUser _user, final Map<String, String> _properties) throws FPMException {

		final Map<String, String> overwriteProperties = (_properties != null) ? _properties : new HashMap<String, String>();

		try {
			registerJob(_session, _task, _user, false, overwriteProperties);
		} catch (FPMException e) {
			LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), e);
			throw e;
		} catch (Exception e) {
			final FPMException newException = new FPMException(e);
			LogUtils.createLog(_session, "administrator", MicroportalManager.BUNDLENAME, xwebCfgService.getApplicationLocale(), MicroportalManager.getInstance().getApplication(_session), newException, true);
			throw newException;
		}
	}
	
	public Date getNextScheduledFire(final UserTask _task) throws SchedulerException {
		CronTrigger trigger = (CronTrigger) this.quartzScheduler.getScheduler().getTrigger(String.valueOf(_task.getId()), Scheduler.DEFAULT_GROUP);
		return trigger.getNextFireTime();
	}

	private synchronized void registerJob(final Session _session, final SystemTask _task, final LoggedUser _user, final boolean _isSchedulled, final Map<String, String> _properties) throws FPMException {

		try {
			// Create/Replace a job and add it to the scheduler.
			final JobDetail job = new JobDetail(String.valueOf(_task.getId()), Scheduler.DEFAULT_GROUP, Class.forName(_task.getProcess().getClassImpl()));
			job.setRequestsRecovery(true);
			job.setDurability(true);
			final JobDataMap jobDataMap = job.getJobDataMap();
			jobDataMap.put(SdmBasicJob.LAUNCHISSCHEDULLED, _isSchedulled);
			jobDataMap.put(SdmBasicJob.LAUNCHUSER, _user.getId());
			jobDataMap.put(SdmBasicJob.LAUNCHLOCALE, _user.getLocale());
			jobDataMap.put(SdmBasicJob.LAUNCHPROPERTIES, _properties);
			this.quartzScheduler.getScheduler().addJob(job, true);
			// Create cron trigger
			if (_task instanceof UserTask) {
				final CronTrigger cronTrigger = new CronTrigger(String.valueOf(_task.getId()), Scheduler.DEFAULT_GROUP, ((UserTask) _task).getCronExpression());
				// Check if a trigger is already associated with this job
				boolean isTriggerAlreadyAssociated = false;
				for (Trigger trig : this.quartzScheduler.getScheduler().getTriggersOfJob(String.valueOf(_task.getId()), Scheduler.DEFAULT_GROUP)) {
					if ((trig.getName().equals(String.valueOf(_task.getId()))) && (Scheduler.DEFAULT_GROUP.equals(trig.getGroup()))) {
						isTriggerAlreadyAssociated = true; // the job already has this trigger associated with it
					}
				}
				// Associate this trigger with the job
				cronTrigger.setJobName(job.getName());
				cronTrigger.setJobGroup(job.getGroup());
				if (isTriggerAlreadyAssociated) {
					// Reschedule the job with the existing trigger.
					final Date initReSchedule = this.quartzScheduler.getScheduler().rescheduleJob(String.valueOf(_task.getId()), Scheduler.DEFAULT_GROUP, cronTrigger);
					if (initReSchedule == null) {
						LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), LogInfoDict.QUARTZ_TRIGGER_NOT_FOUND, new Object[] { _task.getId() }, null);
					} else {
						LogUtils.createLog(_session, _user.getId(), MicroportalManager.BUNDLENAME, _user.getLocale(), MicroportalManager.getInstance().getApplication(_session), LogInfoDict.QUARTZ_NEXT_EVENT, new Object[] { _task.getId(), initReSchedule.toString() }, null);
					}
				} else {
					this.quartzScheduler.getScheduler().scheduleJob(cronTrigger);// Schedule the job with the new trigger.
				}
			} else {
				final SimpleTrigger simpleTrigger = new SimpleTrigger(String.valueOf(_task.getId()), Scheduler.DEFAULT_GROUP, new Date(), null, 0, 0l);
				simpleTrigger.setJobName(job.getName());
				simpleTrigger.setJobGroup(job.getGroup());
				this.quartzScheduler.getScheduler().scheduleJob(simpleTrigger);// Schedule the job with the new trigger.
			}
		} catch (ParseException e) {
			throw new FPMException(LogErrorDict.QUARTZ_ERROR_PARSE_CRON_EXPRESSION, new Object[] { ((UserTask) _task).getCronExpression() }, e);
		} catch (ClassNotFoundException e) {
			throw new FPMException(LogErrorDict.QUARTZ_JOB_CLASS_NOT_FOUND, new Object[] { _task.getProcess().getClassImpl() }, e);
		} catch (Exception e) {
			throw new FPMException(e);
		}
	}
}
