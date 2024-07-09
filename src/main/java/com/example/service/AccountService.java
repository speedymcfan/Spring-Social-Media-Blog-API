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

    public Account findAccount(String username){
        if(username.length() < 1)
            return null;       
        Account account = accountRepository.findAccountByUsername(username);
        if(account == null)
            return new Account(-1, "user9999", "pass");
        return account;
    }

    public Account createAccount(Account account){
        if(account.getUsername() == null)
            return null;
        Account check = findAccount(account.getUsername());
        if(check == null){
            return null;
        }
        if(check.getAccountId() == -1){
            if(account.getPassword().length() < 4)
                return null;
            Account newAccount = new Account(account.getUsername(), account.getPassword());
            accountRepository.save(newAccount);
            return newAccount;
        }
        return new Account(-1, "user", "pass");
    }

    public Account validate(Account account){
        if(account.getUsername() == null)
            return null;
        Account result = findAccount(account.getUsername());
        if(result == null)
            return null;
        if(result.getAccountId() == -1)
            return null;
        if(!result.getPassword().equals(account.getPassword()))
            return null;
        return result;
    }

}
