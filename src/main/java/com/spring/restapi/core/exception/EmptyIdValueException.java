package com.spring.restapi.core.exception;

public class EmptyIdValueException extends RuntimeException {

    public EmptyIdValueException(String Message) {
        super(Message);
    }
}
