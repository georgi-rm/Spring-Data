package com.example.softunigamestore.services.impl;

import com.example.softunigamestore.entities.users.LoginDTO;
import com.example.softunigamestore.entities.users.RegisterDTO;
import com.example.softunigamestore.entities.users.User;
import com.example.softunigamestore.exceptions.UserAlreadyLoggedInException;
import com.example.softunigamestore.exceptions.UserNotLoggedInException;
import com.example.softunigamestore.exceptions.ValidationException;
import com.example.softunigamestore.repositories.UserRepository;
import com.example.softunigamestore.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private User currentUser;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.currentUser = null;

        this.userRepository = userRepository;
    }

    @Override
    public User register(RegisterDTO registerData) {
        if (this.currentUser != null) {
            throw new UserAlreadyLoggedInException("Another user is logged in!");
        }

        ModelMapper mapper = new ModelMapper();
        User toRegister = mapper.map(registerData, User.class);

        long userCount = this.userRepository.count();

        if (userCount == 0) {
            toRegister.setAdmin(true);
        }

        Optional<User> foundUser = userRepository.findByEmail(toRegister.getEmail());

        if (foundUser.isPresent()) {
            throw new ValidationException("User with this e-mail is already registered");
        }

        return this.userRepository.save(toRegister);
    }

    @Override
    public Optional<User> login(LoginDTO loginData) {
        if (this.currentUser != null) {
            throw new UserAlreadyLoggedInException("Another user is logged in!");
        }

        Optional<User> user = this.userRepository.findByEmailAndPassword(
                loginData.getEmail(), loginData.getPassword());

        user.ifPresent(value -> this.currentUser = value);

        return user;
    }

    public User getLoggedUser() {
        return this.currentUser;
    }

    @Override
    public void logout() {
        if (this.currentUser == null) {
            throw new UserNotLoggedInException("Cannot log out. No user was logged in.");
        }
        this.currentUser = null;
    }
}
