package com.tiny.api.service.exception;

import org.springframework.http.HttpStatus;

/**
 * @author admin
 *
 */
public class UrlNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2818561314297327325L;

	public UrlNotFoundException(String message, HttpStatus httpStatus) {
		super(message);

	}

}
