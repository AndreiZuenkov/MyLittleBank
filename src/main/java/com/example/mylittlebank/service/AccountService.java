package com.example.mylittlebank.service;

import com.example.mylittlebank.persistence.model.Account;
import com.example.mylittlebank.persistence.model.User;
import com.example.mylittlebank.persistence.repository.AccountRepo;
import com.example.mylittlebank.service.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountMapper accountMapper;

    public List<Account> findAllUserAccounts(String idFromQuery) {

        List<Account> accountList = accountRepo.findAllByOwner(userService.findUserById(idFromQuery));

        return accountList;
    }

    public boolean checkUserAccount(String idFromQuery, String accountNumberFromQuery) {
        List<Account> accountList = findAllUserAccounts(idFromQuery);
        for (Account account : accountList
        ) {
            if (account.getAccountNumber() == Long.parseLong(accountNumberFromQuery)) {
                return true;
            }
        }
        return false;
    }

    public boolean createAccount(String idFromQuery) {


        if (userService.findUserById(idFromQuery) != null) {

            Account account = new Account(Math.abs(UUID.randomUUID().getMostSignificantBits()),
                    0,
                    LocalDate.now(),
                    LocalDate.of(LocalDate.now().getYear() + 3, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()),
                    userService.findUserById(idFromQuery));

            accountRepo.save(account);
            return true;
        }
        return false;
    }

    public Account findByAccountNumber(String accountNumber) {
        return accountRepo.findByAccountNumber(Long.parseLong(accountNumber));
    }

    public Account findByAccountNumber(long accountNumber) {
        return accountRepo.findByAccountNumber(accountNumber);
    }

    public boolean deleteAccount(String idFromQuery, String accountNumber) {

        if (findByAccountNumber(accountNumber) != null) {
            accountRepo.delete(findByAccountNumber(accountNumber));
            return true;
        }
        return false;
    }

    public void changeAmount(Account account, double amount) {

        account.setAmount(account.getAmount() + amount);
        accountRepo.save(account);

    }

}

