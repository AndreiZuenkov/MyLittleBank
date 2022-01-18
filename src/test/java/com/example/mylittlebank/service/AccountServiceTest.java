package com.example.mylittlebank.service;

import com.example.mylittlebank.persistence.model.Account;
import com.example.mylittlebank.persistence.model.User;
import com.example.mylittlebank.persistence.repository.AccountRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AccountServiceTest {

    @MockBean
    private AccountRepo accountRepo;

    @Autowired
    private AccountService accountService;

    @MockBean
    private UserService userService;


    @Test
    void testFindAllUserAccounts() {

        String idFromQuery = " ";
        User user = new User();
        List<Account> expectedList = new ArrayList<>();

        Mockito.when(userService.findUserById(idFromQuery)).thenReturn(user);

        List<Account> accountList = accountService.findAllUserAccounts(idFromQuery);

        Mockito.verify(accountRepo).findAllByOwner(user);

        Assertions.assertEquals(expectedList, accountList);

    }

    @Test
    void testFindAllUserAccountExpectedNull() {

        List<Account> accountList = accountService.findAllUserAccounts(null);

        Assertions.assertEquals(null, accountList);

    }


    @Test
    void testCheckUserAccount() {

        String idFromQuery = "1";
        String accountNumberFromQuery = "1";
        User user = new User();
        Account account = new Account();
        account.setAccountNumber(1);


        List<Account> expectedList = new ArrayList<>() {{
            add(account);
        }};

        Mockito.when(userService.findUserById(idFromQuery)).thenReturn(user);

        Mockito.when(accountRepo.findAllByOwner(user)).thenReturn(expectedList);

        boolean isCheckSuccess = accountService.checkUserAccount(idFromQuery, accountNumberFromQuery);

        Assertions.assertTrue(isCheckSuccess);

    }

    @Test
    void testCheckUserAccountExpectedNull() {

        boolean isCheckSuccess = accountService.checkUserAccount(null, null);

        Assertions.assertFalse(isCheckSuccess);

    }

    @Test
    void testCreateAccount() {

        String idFromQuery = " ";

        Mockito.when(userService.findUserById(idFromQuery)).thenReturn(new User());

        boolean isAccountCreated = accountService.createAccount(idFromQuery);

        Mockito.verify(accountRepo).save(Mockito.any());

        Assertions.assertTrue(isAccountCreated);

    }

    @Test
    void testDoNotCreateAccount() {

        String idFromQuery = " ";

        Mockito.when(userService.findUserById(idFromQuery)).thenReturn(null);

        boolean isAccountCreated = accountService.createAccount(idFromQuery);

        Assertions.assertFalse(isAccountCreated);

    }


    @Test
    void testFindByAccountNumber() {

        String accountNumber = "1";
        Account expectedAccount = new Account();

        Mockito.when(accountRepo.findByAccountNumber(Long.parseLong(accountNumber))).thenReturn(expectedAccount);

        Account account = accountService.findByAccountNumber(accountNumber);

        Assertions.assertEquals(expectedAccount, account);


    }


    @Test
    void testFindByAccountNumberWithLongAccountNumber() {

        Long accountNumber = 1L;
        Account expectedAccount = new Account();

        Mockito.when(accountRepo.findByAccountNumber(accountNumber)).thenReturn(expectedAccount);

        Account account = accountService.findByAccountNumber(accountNumber);

        Assertions.assertEquals(expectedAccount, account);

    }


    @Test
    void testFindByAccountNumberExpectedNull() {

        Account account = accountService.findByAccountNumber(null);

        Assertions.assertEquals(null, account);

    }

    @Test
    void deleteAccount() {
    }

    @Test
    void testChangeAmount() {

        Account account=new Account();
        double amount=1.0;

        accountService.changeAmount(account, amount);

        Mockito.verify(accountRepo).save(account);


    }
}