package com.spring.restapi.core.exception;

import org.springframework.http.HttpStatus;

public class TokenExpiredException extends RuntimeException{

    private HttpStatus status;

    public TokenExpiredException(String message) {
        super(message);
    }

    public TokenExpiredException() {
        this.status = HttpStatus.FORBIDDEN;
    }

    /**
     * NOT_FOUND
     * return HttpStatus code - 404
     */
    public Integer getCode() {
        return status.value();
    }

    public HttpStatus getStatus() {
        return status;
    }
}
