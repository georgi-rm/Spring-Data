package com.example.accountsystem.services;

import com.example.accountsystem.models.User;
import com.example.accountsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registerUser(User user) {
        User foundUser = repository.getByUsername(user.getUsername());

        if (foundUser == null) {
            repository.save(user);
        }
    }
}
