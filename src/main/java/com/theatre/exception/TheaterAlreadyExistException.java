package com.theatre.exception;

import lombok.Data;

/*
 * Custom Exception thrown when the Theater Name already exists
 */
@Data
public class TheaterAlreadyExistException extends Exception {
	
	/*
	 * First Super Class Constructor
	 */
	String Code;
	public TheaterAlreadyExistException() {
		super();
	}

	public TheaterAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TheaterAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public TheaterAlreadyExistException(String message) {
		super(message);
	}

	/*
	 * Last Super Class Constructor
	 */
	public TheaterAlreadyExistException(Throwable cause) {
		super(cause);
	}
	
	/*
	 * Custom Constructor For Storing Exception with Exception Code
	 */
	public TheaterAlreadyExistException(String message, String Code) {
		super(message);
		this.Code = Code;
	}
}
