package com.example.accountsystem.services;

import com.example.accountsystem.models.Account;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface AccountService {
    void createAccount(Account account);


    void withdrawMoney(BigDecimal money, Long id);

    void transferMoney(BigDecimal money, Long id);
}
