package com.lynxspa.sdm.exceptions;

import com.lynxspa.entities.application.flow.operations.OperationStatus;
import com.lynxspa.exception.FPMExceptionMessage;
import com.lynxspa.exception.FPMExceptionType;

public class ExceptionStateClone implements FPMExceptionMessage{

	private String defaultMessage=null;
	private String messageKey=null;
	private FPMExceptionType type=null;
	
	
	public ExceptionStateClone(OperationStatus _status){
		this.messageKey=_status.getTransitionMessageKey();
		this.defaultMessage=_status.getTransitionMessageKey();
		this.type=((_status.isTransitionMessageError())? FPMExceptionType.ERROR : FPMExceptionType.WARNING);
	}
	
	public String getDefaultMessage() {
		return this.defaultMessage;
	}

	public String getMessageKey() {
		return this.messageKey;
	}

	public FPMExceptionType getType() {
		return this.type;
	}
}
