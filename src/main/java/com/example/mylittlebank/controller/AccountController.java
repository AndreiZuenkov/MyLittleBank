package com.example.mylittlebank.controller;

import com.example.mylittlebank.model.Account;
import com.example.mylittlebank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}/account")
    public List<Account> findAllUserAccounts(@PathVariable String id){

        return accountService.findAllUserAccounts(id);
    }
}
