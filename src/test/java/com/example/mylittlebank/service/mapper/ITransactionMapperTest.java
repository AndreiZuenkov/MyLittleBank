package com.example.mylittlebank.service.mapper;

import com.example.mylittlebank.controller.dto.TransactionDto;
import com.example.mylittlebank.persistence.model.Account;
import com.example.mylittlebank.persistence.model.Transaction;
import com.example.mylittlebank.persistence.model.TransactionType;
import com.example.mylittlebank.persistence.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ITransactionMapperImpl.class})
class ITransactionMapperTest {

    @Autowired
    private ITransactionMapperImpl transactionMapper;

    @Test
    void testMapToTransactionDto() {

        Transaction transaction = new Transaction() {{
            setType(TransactionType.TRANSFER);
            setAmount(1000.0);
            setDateTime(LocalDateTime.now());
        }};


        TransactionDto transactionDto = transactionMapper.mapToTransactionDto(transaction);

        Assertions.assertEquals(transaction.getAmount(), transactionDto.getAmount());
        Assertions.assertEquals(transaction.getType(), transactionDto.getType());
        Assertions.assertEquals(transaction.getDateTime(), transactionDto.getDateTime());

    }

    @Test
    void testMapToTransaction() {

        TransactionDto transactionDto = new TransactionDto() {{
            setType(TransactionType.TRANSFER);
            setAmount(1000.0);
            setDateTime(LocalDateTime.now());
        }};

        Transaction transaction= transactionMapper.mapToTransaction(transactionDto);
        Assertions.assertEquals(transactionDto.getAmount(), transaction.getAmount());
        Assertions.assertEquals(transactionDto.getType(), transaction.getType());
        Assertions.assertEquals(transactionDto.getDateTime(), transaction.getDateTime());

    }
}