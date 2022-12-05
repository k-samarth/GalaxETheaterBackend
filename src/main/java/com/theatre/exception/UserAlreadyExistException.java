package com.theatre.exception;

import lombok.Data;

@Data
public class UserAlreadyExistException extends Exception {

	String Code;
	public UserAlreadyExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserAlreadyExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	public UserAlreadyExistException(String message, String Code) {
		super(message);
		this.Code = Code;
	}
}
