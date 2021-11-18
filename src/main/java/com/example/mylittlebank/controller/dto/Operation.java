package com.example.mylittlebank.controller.dto;

import com.example.mylittlebank.persistence.model.Transaction;
import lombok.Data;

@Data
public class Operation {
    private String idFromQuery;
    private String accountNumberFromQuery;
    private TransactionDto transactionDto;

    public Operation(String idFromQuery, String accountNumberFromQuery, TransactionDto transactionDto) {
        this.idFromQuery = idFromQuery;
        this.accountNumberFromQuery = accountNumberFromQuery;
        this.transactionDto = transactionDto;
    }
}
