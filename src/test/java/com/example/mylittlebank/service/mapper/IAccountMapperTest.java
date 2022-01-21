package com.example.mylittlebank.service.mapper;

import com.example.mylittlebank.controller.dto.AccountDto;
import com.example.mylittlebank.persistence.model.Account;
import com.example.mylittlebank.persistence.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = {IAccountMapperImpl.class})
class IAccountMapperTest {

    @Autowired
    private IAccountMapperImpl accountMapper;


    @Test
    void testMapToAccountDto() {

        User user=new User();

        Account account=new Account(){{
            setAmount(1);
            setOpeningDate(LocalDate.now());
            setOwner(user);
            setValidityPeriod(LocalDate.of(LocalDate.now().getYear() + 3, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));
        }};

        AccountDto accountDto=accountMapper.mapToAccountDto(account);

        Assertions.assertEquals(account.getAmount(),accountDto.getAmount());
        Assertions.assertEquals(account.getOpeningDate(), accountDto.getOpeningDate());
        Assertions.assertEquals(account.getOwner(), accountDto.getOwner());
        Assertions.assertEquals(account.getValidityPeriod(), accountDto.getValidityPeriod());


    }

    @Test
    void mapToAccount() {

        User user=new User();

        AccountDto accountDto=new AccountDto(){{
            setAmount(1);
            setOpeningDate(LocalDate.now());
            setOwner(user);
            setValidityPeriod(LocalDate.of(LocalDate.now().getYear() + 3, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()));
        }};

        Account account=accountMapper.mapToAccount(accountDto);

        Assertions.assertEquals(accountDto.getAmount(),account.getAmount());
        Assertions.assertEquals(accountDto.getOpeningDate(), account.getOpeningDate());
        Assertions.assertEquals(accountDto.getOwner(), account.getOwner());
        Assertions.assertEquals(accountDto.getValidityPeriod(), account.getValidityPeriod());

    }
}