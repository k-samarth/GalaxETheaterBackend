package com.theatre.exception;

import java.awt.event.FocusEvent.Cause;

import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Super;

/*
 * Custom Exception thrown when there is no Content to be shown
 */
@Data
public class NoContentException extends Exception {

	String Code;
	
	/*
	 * First Super Class Constructor
	 */
	public NoContentException() {
		super();
	}

	
	public NoContentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		System.out.println(cause);
	}
	
	public NoContentException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoContentException(String message) {
		super(message);
	}

	/*
	 * Last Super Class Constructor
	 */
	public NoContentException(Throwable cause) {
		super(cause);
	}
	
	/*
	 * Custom Constructor For Storing Exception with Exception Code
	 */
	public NoContentException(String message, String Code) {
		super(message);
		setCode(Code);;
	}
}
