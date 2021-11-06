package com.example.mylittlebank.service;

import com.example.mylittlebank.model.Account;
import com.example.mylittlebank.model.User;
import com.example.mylittlebank.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private UserService userService;

    public List<Account> findAllUserAccounts(String idFromQuery) {


        List<Account> accountList = accountRepo.findAllByOwner(userService.findUserById(idFromQuery));

        return accountList;
    }

    public Account createAccount(String idFromQuery) {

        User user = userService.findUserById(idFromQuery);

        Account account = new Account(Math.abs(UUID.randomUUID().getMostSignificantBits()),
                0,
                LocalDate.now(),
                LocalDate.of(LocalDate.now().getYear() + 3, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()),
                user);

        accountRepo.save(account);

        return account;
    }

    private Account findByAccountNumber(String accountNumber) {
        return accountRepo.findByAccountNumber(Long.parseLong(accountNumber));
    }

    public boolean deleteAccount(String idFromQuery, String accountNumber) {

        if (findByAccountNumber(accountNumber) != null) {
            accountRepo.delete(findByAccountNumber(accountNumber));
            return true;
        }
        return false;
    }

    public boolean changeAmount(String idFromQuery, String accountNumber, String amount) {

        Account account = findByAccountNumber(accountNumber);
        if (userService.findUserById(idFromQuery) != null && chekOperation(accountNumber, amount)) {
                account.setAmount(incrementAmount(accountNumber, amount));
                accountRepo.save(account);
                return true;

        }
        return false;
    }

    private boolean chekOperation(String accountNumber, String amountFromQuery) {
        double amount = Double.parseDouble(amountFromQuery);
        int temp = Double.compare(0.0, Math.signum(amount));

        if (temp == -1) {
            return true;
        } else if (temp == 1) {
            if (findByAccountNumber(accountNumber).getAmount() > Math.abs(amount)) {
                return true;
            }
        } else if (temp == 0) return false;

        return false;
    }


    private double incrementAmount(String accountNumber, String amountFromQuery) {

        double amount = Double.parseDouble(amountFromQuery);

        return findByAccountNumber(accountNumber).getAmount() + amount;

    }

}

