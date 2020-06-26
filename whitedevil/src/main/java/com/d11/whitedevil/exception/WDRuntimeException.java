package com.d11.whitedevil.exception;

public class WDRuntimeException extends RuntimeException {

	public WDRuntimeException(String exMessage, Exception exception) {
		super(exMessage, exception);
	}

	public WDRuntimeException(String exMessage) {
		super(exMessage);
	}

}
