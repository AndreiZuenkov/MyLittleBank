package com.example.mylittlebank.service;

import com.example.mylittlebank.controller.dto.TransactionDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    void testDoTransaction() {

        String idFromQuery="1";
        String accountNumberFromQuery="1";
        TransactionDto transactionDto=new TransactionDto();



    }
}