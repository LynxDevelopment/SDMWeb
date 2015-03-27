package com.lynxspa.microportal.dictionaries;

import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.entities.application.logs.utils.LogLevel;
import com.lynxspa.exception.FPMExceptionMessage;
import com.lynxspa.exception.FPMExceptionType;

public enum WebLogWarningDict implements LogDictAdapter, FPMExceptionMessage {

	USER_NOT_EXIST("error.user.not.exist", "User {0} not exist."),
	USER_GROUP_NOT_EXIST("error.user.group.not.exist", "User {0} hasn''t group {1} or not exist."),
	ANY_GROUP_NOT_EXIST("error.user.any.group.not.exist", "Any group not exist."),
	CANT_CHANGE_PASSWORD_FROM_DOMAIN("error.cant.change.password.from.domain", "Can''t change password from domain {0}."),
	BOXLET_NOT_EXIST("error.boxlet.not.exist", "Boxlet {0} not exist."),
	BOXLET_CONFIGURATION_NOT_EXIST("error.boxlet.configuration.not.exist", "Configuration {0} at position {1} of boxlet {2} not exist."),
	BOXLET_CONFIGURATION_CONTENT_NOT_EXIST("error.boxlet.configuration.content.not.exist", "Configuration {0} at position {1} of boxlet {2} content not exist."),
	NEW_ALREADY_EXIST("error.new.already.exist", "Already exist another new with the same identifier."),
	NEW_NOT_EXIST("error.new.not.exist", "New {0} not exist."),
	NEW_STATUS_NOT_EXIST("error.new.status.not.exist", "Status {0} for new {1} not exist."),
	NEW_GROUP_NOT_EXIST("error.new.group.not.exist", "Group {0} for new {1} not exist."),
	UNKNOWN_APPLICATION("error.config.update.application", "Unknown application {0}."),
	MENU_NOT_EXIST("error.menu.not.exist", "Menu {0} not exist."),
	MENU_HAVE_ALREADY_CHILDS("error.menu.has.already.childs", "Menu {0} have already some childs. Delete all childs before delete parent."),
	BOXLET_OR_MENU_ALREADY_EXIST("error.boxlet.or.menu.already.exist", "Already exist a menu or container with id {0}."), 
	PROCESS_NAME_DUPLICATED("error.process.name.duplicated", "Already exists a process with this name."), 
	PROCESS_NOT_FOUND("error.process.not.found", "Can not find the process."), 
	QUARTZ_TASK_NOT_FOUND("quartz.task.not.found", "Task not found.");

	private String	messageKey		= null;
	private String	defaultMessage	= null;

	WebLogWarningDict(String _key, String _defaultMessage) {
		this.messageKey = _key;
		this.defaultMessage = _defaultMessage;
	}

	@Override
	public String getMessageKey() {
		return this.messageKey;
	}

	@Override
	public String getDefaultMessage() {
		return this.defaultMessage;
	}

	@Override
	public LogLevel getLevel() {
		return LogLevel.WARNING;
	}

	@Override
	public FPMExceptionType getType() {
		return FPMExceptionType.WARNING;
	}
}
