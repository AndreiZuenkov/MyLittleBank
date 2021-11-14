package com.example.mylittlebank.service.mapper;

import com.example.mylittlebank.controller.dto.AccountDto;
import com.example.mylittlebank.persistence.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

    public AccountDto mapToAccountDto(Account account) {

        AccountDto accountDto = new AccountDto();

        accountDto.setAmount(account.getAmount());
        accountDto.setOpeningDate(account.getOpeningDate());
        accountDto.setValidityPeriod(account.getValidityPeriod());
        accountDto.setOwner(account.getOwner());
        return accountDto;
    }

    public Account mapToAccount(AccountDto accountDto) {

        Account account = new Account();
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAmount(accountDto.getAmount());
        account.setOpeningDate(accountDto.getOpeningDate());
        account.setValidityPeriod(accountDto.getValidityPeriod());
        account.setOwner(accountDto.getOwner());

        return account;
    }
}
