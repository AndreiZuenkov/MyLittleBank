package com.example.mylittlebank.controller;

import com.example.mylittlebank.persistence.model.Account;
import com.example.mylittlebank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}/account")
    public List<Account> findAllUserAccounts(@PathVariable String id) {

        return accountService.findAllUserAccounts(id);
    }

    @PostMapping("/{id}/")
    public Account createAccount(@PathVariable String id) {

        accountService.createAccount(id);
        return null;
    }

    @DeleteMapping("/{id}/{accountNumber}")
    public void deleteAccount(@PathVariable String id, @PathVariable String accountNumber) {
        accountService.deleteAccount(id, accountNumber);
    }

}
