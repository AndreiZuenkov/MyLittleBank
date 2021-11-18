package com.example.mylittlebank.controller;

import com.example.mylittlebank.controller.dto.Response;
import com.example.mylittlebank.exception.AccountException;
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
    public Response createAccount(@PathVariable String id) throws AccountException {

        if (!accountService.createAccount(id)) {
            throw new AccountException("Account not created");
        }

        return new Response("Account created");
    }

    @DeleteMapping("/{id}/{accountNumber}")
    public void deleteAccount(@PathVariable String id, @PathVariable String accountNumber) {
        accountService.deleteAccount(id, accountNumber);
    }

}
