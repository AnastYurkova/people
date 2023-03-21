package com.ku.people.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException (String message, Throwable cause) {
        super(message, cause);
    }
}
