package com.lynxspa.sdm.exceptions;

import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.entities.application.logs.utils.LogLevel;
import com.lynxspa.exception.FPMExceptionMessage;
import com.lynxspa.exception.FPMExceptionType;

public enum WarningDict implements LogDictAdapter,FPMExceptionMessage {
	

	//Event providers
	EVENTPROVIDERNOTFOUND("warning.update.event.provider.not.found","Id not existent."),
	EVENTPROVIDERIDALREADYEXIST("warning.new.event.provider.id.already.exist","Id already exist."),
	//Normalization
	IMPOSIBLE_NORMALIZATION_TEST_WITHOUT_EXAMPLES("warning.imposible.normalization.test.without.examples","Can''t make normalization test without examples"),
	//Message formats
	MESSAGE_FORMAT_CLUSTER_ASSIGNATION_ALLREADY_EXIST("warning.message.format.cluster.assignation.allready.exist","Cluster {0} of domain {1} assignation allready exist."),
	MESSAGE_TYPE_ALLREADY_EXIST("warning.message.type.allready.exist","Message type {0} allready exist."),
	EXISTING_MESSAGES_RELATED_TO_THIS_MESSAGEFORMAT("warning.existing.messages.related.to.this.format","Exist {0} messages related to this format, please delete them defore delete format."),
	EXISTING_MESSAGES_RELATED_TO_THIS_MESSAGETYPE("warning.existing.messages.related.to.this.type","Exist {0} messages related to this type, please delete them defore delete type.");
	

	private String key=null;
	private String defaultMessage=null;
	
	
	WarningDict(String _key,String _defaultMessage){
		this.key=_key;
		this.defaultMessage=_defaultMessage;
	}
	
	public String getMessageKey() {
		return this.key;
	}
	public String getDefaultMessage() {
		return this.defaultMessage;
	}
	public FPMExceptionType getType() {
		return FPMExceptionType.WARNING;
	}
	public LogLevel getLevel() {
		return LogLevel.WARNING;
	}
}
