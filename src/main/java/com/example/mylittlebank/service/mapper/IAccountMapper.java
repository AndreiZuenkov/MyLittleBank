package com.example.mylittlebank.service.mapper;


import com.example.mylittlebank.controller.dto.AccountDto;
import com.example.mylittlebank.persistence.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(implementationName = "IAccountMapperImpl")
public interface IAccountMapper {

    IAccountMapper ACCOUNT_MAPPER = Mappers.getMapper(IAccountMapper.class);

    AccountDto mapToAccountDto(Account account);

    Account mapToAccount(AccountDto accountDto);

}
