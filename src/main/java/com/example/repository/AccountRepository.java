package com.example.repository;

import com.example.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>{
    
    Account findAccountByUsername(String username);

    Account findAccountByAccountId(int accountId);
}
