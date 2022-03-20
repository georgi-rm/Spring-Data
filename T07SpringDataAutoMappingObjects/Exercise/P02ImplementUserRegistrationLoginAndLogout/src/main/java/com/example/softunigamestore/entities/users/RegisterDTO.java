package com.example.softunigamestore.entities.users;

import com.example.softunigamestore.exceptions.ValidationException;
import com.example.softunigamestore.validators.Validator;

public class RegisterDTO {
    private final String email;
    private final String password;
    private final String confirmPassword;
    private final String fullName;

    public RegisterDTO(String[] commandParts) {
        this.email = commandParts[1];
        this.password = commandParts[2];
        this.confirmPassword = commandParts[3];
        this.fullName = commandParts[4];

        this.validate();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void validate() {
        Validator.validateEmail(email);

        Validator.validatePassword(password);

        if (!password.equals(confirmPassword)) {
            throw new ValidationException("Password and confirm password must match!");
        }
    }

}
