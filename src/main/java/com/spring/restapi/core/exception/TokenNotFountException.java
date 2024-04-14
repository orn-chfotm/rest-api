package com.spring.restapi.core.exception;

import org.springframework.http.HttpStatus;

public class TokenNotFountException extends RuntimeException{

    private HttpStatus status;

    public TokenNotFountException(String message) {
        super(message);
    }

    public TokenNotFountException() {
        this.status = HttpStatus.FORBIDDEN;
    }

    /**
     * NOT_FOUND
     * return HttpStatus code - 403
     */
    public Integer getCode() {
        return status.value();
    }

    public HttpStatus getStatus() {
        return status;
    }
}
