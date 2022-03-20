package com.example.softunigamestore.entities.users;

import com.example.softunigamestore.validators.Validator;

public class LoginDTO {
    private final String email;
    private final String password;

    public LoginDTO(String[] commandLineParts) {
        this.email = commandLineParts[1];
        this.password = commandLineParts[2];

        this.validate();
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void validate() {
        Validator.validateEmail(email);

        Validator.validatePassword(password);
    }
}
