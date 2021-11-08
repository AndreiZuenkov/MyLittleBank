package com.example.mylittlebank.service.mapper;

import com.example.mylittlebank.controller.dto.TransactionDto;
import com.example.mylittlebank.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapper {

    public TransactionDto mapToTransactionDto(Transaction transaction){

        TransactionDto transactionDto=new TransactionDto();

        transactionDto.setType(transaction.getType());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setDateTime(transaction.getDateTime());
        transactionDto.setAccount(transaction.getAccount());

        return transactionDto;

    }

    public Transaction mapToTransaction(TransactionDto transactionDto){

        Transaction transaction=new Transaction();

        transaction.setType(transactionDto.getType());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setDateTime(transactionDto.getDateTime());
        transaction.setAccount(transactionDto.getAccount());

        return transaction;
    }
}
