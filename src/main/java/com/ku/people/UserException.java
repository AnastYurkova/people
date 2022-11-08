package com.ku.people;

public class UserException extends RuntimeException{
    public UserException(String message, Throwable cause){
        super(message, cause);
    }
}
