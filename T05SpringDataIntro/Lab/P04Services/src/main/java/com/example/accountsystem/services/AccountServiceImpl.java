package com.example.accountsystem.services;

import com.example.accountsystem.models.Account;
import com.example.accountsystem.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Account account = repository.findAccountById(id);

        if (account == null) {
            return;
        }

        if (account.getUser() == null ) {
            return;
        }

        if (account.getBalance().compareTo(money) < 0) {
            return;
        }

        account.setBalance(account.getBalance().subtract(money));
    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {
        Account account = repository.findAccountById(id);

        if (account == null) {
            return;
        }

        if (account.getUser() == null ) {
            return;
        }

        if (money.signum() <= 0 ) {
            return;
        }

        account.setBalance(account.getBalance().add(money));
    }
}
