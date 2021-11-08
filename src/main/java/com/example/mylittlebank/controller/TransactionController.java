package com.example.mylittlebank.controller;

import com.example.mylittlebank.model.Transaction;
import com.example.mylittlebank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("{id}/{accountNumber}/transaction")
    public void doTransaction(@PathVariable String id, @PathVariable String accountNumber, @RequestBody Transaction transactionDto){
        transactionService.doTransaction(id, accountNumber,transactionDto);
    }
}
