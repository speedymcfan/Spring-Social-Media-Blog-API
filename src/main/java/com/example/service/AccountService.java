package com.example.service;

import com.example.entity.*;
import com.example.repository.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountService {
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account findAccount(Account account){
        return null;
    }

    public Account createAccount(Account account){
        return null;
    }

    public Account validate(Account account){
        return null;
    }

}
