package com.lynxspa.microportal.dictionaries;

import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.entities.application.logs.utils.LogLevel;

public enum WebLogAuditDict implements LogDictAdapter {

	USER_INSERTED("info.user.inserted", "User {0} has been created."),
	USER_UPDATED("info.user.updated", "User {0} has been updated."),
	USER_DELETED("info.user.deleted", "User {0} has been deleted."),
	USER_PASSWORD_CHANGE("info.user.password.change", "User {0} has been changed his password."),
	BOXLET_UPDATED("info.boxlet.updated", "Boxlet {0} has been updated."),
	NEW_INSERTED("info.new.inserted", "New {0} has been created."),
	NEW_CONTENT_UPDATED("info.new.content.updated", "New {0} content has been modified."),
	NEW_STATUS_UPDATED("info.new.status.updated", "New {0} has been change his status from {1} to {2}."),
	NEW_DESTINATARY_UPDATED("info.new.destinatary.updated", "New {0} has been change his destinatary from {1} to {2}."),
	NEW_DELETED("info.new.deleted", "New {0} has been deleted."),
	MENU_INSERTED("info.menu.inserted", "Menu {0} has been created."),
	MENU_UPDATED("info.menu.updated", "Menu {0} has been modified."),
	MENU_DELETED("info.menu.deleted", "Menu ''{0}'' has been deleted."),
	MENU_POSITION_UPDATED("info.menu.position.updated", "Menu {0} position has been moved from {1} to {2}."),
	MENU_STATUS_UPDATED("info.menu.status.updated", "Menu {0} status has been changed from {1} to {2}.");

	private String	messageKey		= null;
	private String	defaultMessage	= null;

	WebLogAuditDict(String _key, String _defaultMessage) {
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
		return LogLevel.INFO;
	}
}
