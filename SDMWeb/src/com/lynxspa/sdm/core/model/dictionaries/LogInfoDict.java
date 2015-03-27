package com.lynxspa.sdm.core.model.dictionaries;

import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.entities.application.logs.utils.LogLevel;

public enum LogInfoDict implements LogDictAdapter {
	QUARTZ_TRIGGER_NOT_FOUND("quartz.trigger.not.found", "Trigger id: {0} not found !. Not change scheduling event"),
	QUARTZ_NEXT_EVENT("quartz.next.event", "Next event from scheduling {0} : {1}"),

	;

	private String	messageKey		= null;
	private String	defaultMessage	= null;

	LogInfoDict(String _key, String _defaultMessage) {
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
		return LogLevel.INFO;
	}
}
