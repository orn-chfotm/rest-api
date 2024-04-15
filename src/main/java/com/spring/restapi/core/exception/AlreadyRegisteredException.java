package com.spring.restapi.core.exception;

import org.springframework.http.HttpStatus;

public class AlreadyRegisteredException extends RuntimeException{

    private HttpStatus status = HttpStatus.CONFLICT;

    public AlreadyRegisteredException(String message) {
        super(message);
    }

    /**
     * NOT_FOUND
     * return HttpStatus code - 409
     */
    public Integer getCode() {
        return status.value();
    }

    public HttpStatus getStatus() {
        return status;
    }
}
