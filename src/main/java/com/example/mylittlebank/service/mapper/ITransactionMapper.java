package com.example.mylittlebank.service.mapper;

import com.example.mylittlebank.controller.dto.TransactionDto;
import com.example.mylittlebank.persistence.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ITransactionMapper {

    ITransactionMapper TRANSACTION_MAPPER= Mappers.getMapper(ITransactionMapper.class);

    TransactionDto mapToTransactionDto(Transaction transaction);

    Transaction mapToTransaction(TransactionDto transactionDto);


}
