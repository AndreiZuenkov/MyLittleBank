package com.example.mylittlebank.service.mapper;

import com.example.mylittlebank.controller.dto.TransactionDto;
import com.example.mylittlebank.persistence.model.Transaction;
import com.example.mylittlebank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapper {

    @Autowired
    private AccountService accountService;

    public TransactionDto mapToTransactionDto(Transaction transaction){

        TransactionDto transactionDto=new TransactionDto();

        transactionDto.setType(transaction.getType());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setDateTime(transaction.getDateTime());
        transactionDto.setAccountNumber(transaction.getAccount().getAccountNumber());

        return transactionDto;

    }

    public Transaction mapToTransaction(TransactionDto transactionDto){

        Transaction transaction=new Transaction();

        transaction.setType(transactionDto.getType());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setDateTime(transactionDto.getDateTime());
        transaction.setAccount(accountService.findByAccountNumber(transactionDto.getAccountNumber()));

        return transaction;
    }
}
