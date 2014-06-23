package com.desmond.servicebuilder.exception;

public class NoSuchDBTypeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3303107000040765842L;

	public NoSuchDBTypeException() {
		super();
	}

	public NoSuchDBTypeException(String message) {
		super(message);
	}

	public NoSuchDBTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchDBTypeException(Throwable cause) {
		super(cause);
	}
}
