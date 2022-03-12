package com.example.accountsystem.services;

import com.example.accountsystem.models.User;
import com.example.accountsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registerUser(User user) {
        User foundUser = repository.findByUsername(user.getUsername());

        if (foundUser == null) {
            repository.save(user);
        }
    }
}
