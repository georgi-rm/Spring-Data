package com.example.accountsystem;

import com.example.accountsystem.models.Account;
import com.example.accountsystem.models.User;
import com.example.accountsystem.services.AccountService;
import com.example.accountsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) {

        User userOne = new User("Ivan", 33);
        User userTwo = new User("Ivan", 24);
        User userThree = new User("Pesho", 24);

        userService.registerUser(userOne);
        userService.registerUser(userTwo);

        Account account = new Account(new BigDecimal(25000));
        account.setUser(userThree);
        userThree.addAccount(account);

        userService.registerUser(userThree);
        accountService.createAccount(account);
        accountService.withdrawMoney(new BigDecimal(20000), account.getId());
        accountService.transferMoney(new BigDecimal(30000), account.getId());
    }
}
