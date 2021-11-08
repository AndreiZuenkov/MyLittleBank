package com.example.mylittlebank.service;

import com.example.mylittlebank.model.Transaction;
import com.example.mylittlebank.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;


    public boolean doTransaction(String id, String accountNumber, Transaction transaction){

        return false;
    }
}
