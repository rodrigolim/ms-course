package com.developer.hrworker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -5636850012025094699L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
