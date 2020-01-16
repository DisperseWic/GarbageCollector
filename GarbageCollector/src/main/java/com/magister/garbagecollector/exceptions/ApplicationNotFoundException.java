package com.magister.garbagecollector.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApplicationNotFoundException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ApplicationNotFoundException(String id) {
		super("could not find application with id '" + id + "'.");
	}
}
