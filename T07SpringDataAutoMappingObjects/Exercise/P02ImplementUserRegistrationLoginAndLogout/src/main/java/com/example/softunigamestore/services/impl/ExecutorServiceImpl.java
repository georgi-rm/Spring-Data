package com.example.softunigamestore.services.impl;

import com.example.softunigamestore.entities.users.LoginDTO;
import com.example.softunigamestore.entities.users.RegisterDTO;
import com.example.softunigamestore.entities.users.User;
import com.example.softunigamestore.exceptions.UserNotLoggedInException;
import com.example.softunigamestore.services.ExecutorService;
import com.example.softunigamestore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExecutorServiceImpl implements ExecutorService {
    private final UserService userService;

    @Autowired
    public ExecutorServiceImpl(UserService userService) {
        this.userService = userService;
    }


    @Override
    public String execute(String commandLine) {
        String[] commandParts = commandLine.split("\\|");

        String commandName = commandParts[0];

        return switch (commandName) {
            case COMMAND_REGISTER_USER -> registerUser(commandParts);
            case COMMAND_LOGIN_USER -> loginUser(commandParts);
            case COMMAND_LOGOUT_USER -> logoutUser();

            default -> "Unknown command";
        };
    }


    private String logoutUser() {
        User loggedUser = this.userService.getLoggedUser();
        if (loggedUser == null) {
            throw new UserNotLoggedInException("Cannot log out. No user was logged in.");
        }
        this.userService.logout();

        return String.format("User %s successfully logged out.",
                loggedUser.getFullName());
    }

    private String loginUser(String[] commandParts) {
        LoginDTO loginData = new LoginDTO(commandParts);

        Optional<User> user = userService.login(loginData);

        if (user.isPresent()) {
            return String.format("Successfully logged in %s",
                    user.get().getFullName());
        }

        return "Incorrect username / password";
    }

    private String registerUser(String[] commandParts) {
        RegisterDTO registerData = new RegisterDTO(commandParts);
        User user = userService.register(registerData);

        return String.format("%s was registered", user.getFullName());
    }
}
