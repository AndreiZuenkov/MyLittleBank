package com.example.mylittlebank.service;

import com.example.mylittlebank.model.Account;
import com.example.mylittlebank.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    public List<Account> findAllUserAccounts(String idFromQuery) {

        Long id=Long.parseLong(idFromQuery);
        List<Account> accountList=accountRepo.findAllByOwner(id);

        return accountList;
    }
}
