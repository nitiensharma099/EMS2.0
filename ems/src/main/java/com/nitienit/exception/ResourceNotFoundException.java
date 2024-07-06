package com.nitienit.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceNotFoundException extends RuntimeException {

	private static final Logger log = LoggerFactory.getLogger(ResourceNotFoundException.class);

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
		log.info("[ResourceNotFoundException] constructor called");
	}

}
