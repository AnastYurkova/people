package com.ku.people.exception;

public class RepositoryException extends RuntimeException{
    public RepositoryException() {
        super();
    }

    public RepositoryException(String message, Throwable cause){
        super(message, cause);
    }

    public RepositoryException(String message) {
        super(message);
    }
}
