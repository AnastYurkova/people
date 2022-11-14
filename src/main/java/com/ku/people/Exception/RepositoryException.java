package com.ku.people.Exception;

public class RepositoryException extends RuntimeException{
    public RepositoryException() {
        super();
    }

    public RepositoryException(String message, Throwable cause){
        super(message, cause);
    }
}
