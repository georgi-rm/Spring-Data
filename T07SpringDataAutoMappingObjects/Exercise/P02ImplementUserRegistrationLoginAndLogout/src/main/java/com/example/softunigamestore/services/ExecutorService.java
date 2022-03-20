package com.example.softunigamestore.services;

import org.springframework.stereotype.Service;

public interface ExecutorService {
    String COMMAND_REGISTER_USER = "RegisterUser";
    String COMMAND_LOGIN_USER = "LoginUser";
    String COMMAND_LOGOUT_USER = "Logout";

    String execute(String command);
}
