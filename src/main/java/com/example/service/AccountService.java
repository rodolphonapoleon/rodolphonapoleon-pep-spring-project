package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account) {   
        if(account.getUsername().length() != 0 && account.getPassword().length() >= 4 && accountRepository.findAccountByUsername(account.getUsername()) == null){
            return accountRepository.save(account);
        } else return null;      
        
    }

    public Account verifyAccount(Account account) {
        if (account.getUsername().length() != 0 && account.getPassword().length() >= 4){
            Account retrievedAccount = accountRepository.findAccountByUsername(account.getUsername());
            if(retrievedAccount != null && retrievedAccount.getPassword().equals(account.getPassword())){
                return retrievedAccount;
            } else return null;
        } else return null; 
    }
}
