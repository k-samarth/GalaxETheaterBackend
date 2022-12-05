package com.theatre.exception;

import java.awt.event.FocusEvent.Cause;

import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Super;

@Data
public class NoContentException extends Exception {

	String Code;
	
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

	public NoContentException(Throwable cause) {
		super(cause);
	}
	
	public NoContentException(String message, String Code) {
		super(message);
		setCode(Code);;
	}
}
