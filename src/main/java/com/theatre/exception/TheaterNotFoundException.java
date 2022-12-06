package com.theatre.exception;

/*
 * Custom Exception thrown when there is no Theater in the Database
 */
public class TheaterNotFoundException extends Exception {
	String Code;
	
	/*
	 * First Super Class Constructor
	 */
	public TheaterNotFoundException() {
		super();
	}

	public TheaterNotFoundException(String message) {
		super(message);
	}

	public TheaterNotFoundException(Throwable cause) {
		super(cause);
	}

	public TheaterNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/*
	 * Last Super Class Constructor
	 */
	public TheaterNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	/*
	 * Custom Constructor For Storing Exception with Exception Code
	 */
	public TheaterNotFoundException(String message,String code) {
		super(message);
		this.Code = code;
	}

	/*
	 * Constructor for variables
	 */
	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

}

