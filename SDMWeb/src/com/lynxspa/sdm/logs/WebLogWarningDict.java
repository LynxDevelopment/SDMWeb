package com.lynxspa.sdm.logs;

import com.lynxspa.entities.application.logs.utils.LogDictAdapter;
import com.lynxspa.entities.application.logs.utils.LogLevel;
import com.lynxspa.exception.FPMExceptionMessage;
import com.lynxspa.exception.FPMExceptionType;

public enum WebLogWarningDict implements LogDictAdapter,FPMExceptionMessage {

	//CONFIGURATION
	UNKNOWN_APPLICATION("error.config.update.application","Unknown application {0}."),
	//	Domains
	UNKNOWN_DOMAIN("error.domain.unkown.domain","Unknown domain {0} of application {1}."),
	//SECURITIES
	//	Planification
	UNKNOWN_PLANIFICATION("error.unknown.planification","Unknown planification {0}"),
	UNKNOWN_PLANIFICATIONPROCESS("error.unknown.planification.process","Unknown planification process {0}");

	
	private String messageKey=null;
	private String defaultMessage=null;
	
	
	WebLogWarningDict(String _key,String _defaultMessage){
		this.messageKey=_key;
		this.defaultMessage=_defaultMessage;
	}

	
	public String getMessageKey() {
		return this.messageKey;
	}
	public String getDefaultMessage() {
		return this.defaultMessage;
	}
	public LogLevel getLevel() {
		return LogLevel.WARNING;
	}
	public FPMExceptionType getType() {
		return FPMExceptionType.WARNING;
	}
}
