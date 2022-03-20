package com.example.softunigamestore.exceptions;

public class UserNotLoggedInException extends RuntimeException{

    public UserNotLoggedInException(String reason) {
        super(reason);
    }
}
