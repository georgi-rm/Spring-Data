package com.example.softunigamestore.exceptions;

public class UserAlreadyLoggedInException extends RuntimeException{
    public UserAlreadyLoggedInException(String reason) {
        super(reason);
    }
}
