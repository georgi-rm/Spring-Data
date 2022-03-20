package com.example.softunigamestore.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException(String reason) {
        super(reason);
    }
}
