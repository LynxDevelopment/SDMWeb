package com.lynxspa.sdm.core.model.dictionaries;

import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.entities.application.logs.utils.LogLevel;
import com.lynxspa.exception.FPMExceptionMessage;
import com.lynxspa.exception.FPMExceptionType;

public enum LogErrorDict implements LogDictAdapter, FPMExceptionMessage {
	// QUARTZ
	QUARTZ_ERROR_SCHEDULER("quartz.error.scheduler", "Error in scheduler."),
	QUARTZ_ERROR_PARSE_CRON_EXPRESSION("quartz.error.parse.cron.expression", "Error in parse cron expression {0}."),
	QUARTZ_JOB_CLASS_NOT_FOUND("quartz.job.class.not.found", "Job class {0} not found."),
	PROCESS_REQUIRE_NOT_ADVANCED_SCHEDULE_ERROR("process.require.not.advanced.schedule.error", "This process require associate a non advanced schedule cron expression"),
	// TASKS
	TASKRECOVERYPOINT("error.task.recovery.point.not.found", "Task ''{0}'' recovery point stage ''{1}'' progress ''{2}'' doesn''t found"),
	TASKSCHEDULEDEXECUTIONFAIL("error.task.scheduled.execution.fail", "Task ''{0}'' scheduled execution has failed"),
	TASKMANUALEXECUTIONFAIL("error.task.manual.execution.fail", "Task ''{0}'' manual execution has failed"), ;

	private String	messageKey		= null;
	private String	defaultMessage	= null;

	LogErrorDict(String _key, String _defaultMessage) {
		this.messageKey = _key;
		this.defaultMessage = _defaultMessage;
	}

	public String getMessageKey() {
		return this.messageKey;
	}

	public String getDefaultMessage() {
		return this.defaultMessage;
	}

	public LogLevel getLevel() {
		return LogLevel.ERROR;
	}

	public FPMExceptionType getType() {
		return FPMExceptionType.ERROR;
	}
}
