package com.example.accountsystem.repositories;

import com.example.accountsystem.models.Account;
import com.example.accountsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountById(Long id);

    Account findByUserAndBalance(User user, BigDecimal balance);
}
