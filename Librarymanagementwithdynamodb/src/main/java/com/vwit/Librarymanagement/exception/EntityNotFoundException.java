package com.vwit.Librarymanagement.exception;

import lombok.AllArgsConstructor;

public class EntityNotFoundException extends RuntimeException {

	private String msg;
	public EntityNotFoundException(String msg) {
		super(msg);
	}
	
}
