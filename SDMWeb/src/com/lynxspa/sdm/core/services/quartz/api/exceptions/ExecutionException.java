package com.lynxspa.sdm.core.services.quartz.api.exceptions;

public class ExecutionException extends Exception {
	private static final long	serialVersionUID	= -9106389212239980588L;

	public ExecutionException() {
		super();
	}

	public ExecutionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExecutionException(String message) {
		super(message);
	}

	public ExecutionException(Throwable cause) {
		super(cause);
	}

}
