package com.fernandobarbosa.services.exceptions;

public class DataIntegritException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DataIntegritException(String msg) {
		super(msg);
	}
	
	public DataIntegritException(String msg, Throwable value) {
		super(msg, value);
	}
}
