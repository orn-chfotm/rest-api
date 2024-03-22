package com.spring.restapi.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class NotFoundDataException extends RuntimeException{

    private HttpStatus status;

    public NotFoundDataException(String message) {
        super(message);
    }

    public NotFoundDataException() {
        this.status = HttpStatus.NOT_FOUND;
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
