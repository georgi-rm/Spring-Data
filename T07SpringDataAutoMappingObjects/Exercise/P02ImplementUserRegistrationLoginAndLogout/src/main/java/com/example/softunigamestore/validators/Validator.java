package com.example.softunigamestore.validators;

import com.example.softunigamestore.exceptions.ValidationException;

public class Validator {

    public static void validateEmail(String email) {
        int indexOfAt = email.indexOf("@");
        int indexOfDot = email.lastIndexOf(".");
        if (indexOfAt < 0 || indexOfDot < 0 || indexOfAt > indexOfDot) {
            throw new ValidationException("Incorrect email.");
        }
    }

    public static void validatePassword(String password) {
        if (password.length() < 6) {
            throw new ValidationException("Password must be at least 6 symbols");
        }

        if (!checkIfPasswordContainsRequiredSymbols(password)) {
            throw new ValidationException("Password must must contain at least 1 uppercase, 1 lowercase letter and 1 digit");
        }
    }


    private static boolean checkIfPasswordContainsRequiredSymbols(String password) {
        char currentSymbol;
        boolean isCapitalFound = false;
        boolean isLowerCaseFound = false;
        boolean isNumberFound = false;

        for (int i = 0; i < password.length(); i++) {
            currentSymbol = password.charAt(i);

            if (Character.isDigit(currentSymbol)) {
                isNumberFound = true;
            } else if (Character.isUpperCase(currentSymbol)) {
                isCapitalFound = true;
            } else if (Character.isLowerCase(currentSymbol)) {
                isLowerCaseFound = true;
            }

            if (isNumberFound && isCapitalFound && isLowerCaseFound)
                return true;
        }
        return false;
    }
}
