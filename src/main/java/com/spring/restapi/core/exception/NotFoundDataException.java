package com.spring.restapi.core.exception;

public class NotFoundDataException extends RuntimeException{

    public NotFoundDataException(String message) {
        super(message);
    }
}
