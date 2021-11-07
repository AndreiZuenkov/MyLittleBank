package com.example.mylittlebank.service;

import com.example.mylittlebank.controller.dto.AccountDto;
import com.example.mylittlebank.model.Account;
import com.example.mylittlebank.model.User;
import com.example.mylittlebank.repository.AccountRepo;
import com.example.mylittlebank.service.mapper.AccountMapper;
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

    @Autowired
    private AccountMapper accountMapper;

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

    private Account findByAccountNumber(long accountNumber) {
        return accountRepo.findByAccountNumber(accountNumber);
    }

    public boolean deleteAccount(String idFromQuery, String accountNumber) {

        if (findByAccountNumber(accountNumber) != null) {
            accountRepo.delete(findByAccountNumber(accountNumber));
            return true;
        }
        return false;
    }

    public boolean changeAmount(String idFromQuery, String accountNumber, AccountDto accountDto) {

        Account accountFromDb = findByAccountNumber(accountNumber);

        Account tempAccount = accountMapper.mapToAccount(accountDto);

        if (userService.findUserById(idFromQuery) != null && chekOperation(accountNumber, tempAccount.getAmount())) {
            accountFromDb.setAmount(incrementAmount(accountNumber, tempAccount.getAmount()));
            accountRepo.save(accountFromDb);
            return true;

        }
        return false;
    }

    private boolean chekOperation(String accountNumber, double amountFromQuery) {

        int temp = Double.compare(0.0, Math.signum(amountFromQuery));

        if (temp == -1) {
            return true;
        } else if (temp == 1) {
            if (findByAccountNumber(accountNumber).getAmount() > Math.abs(amountFromQuery)) {
                return true;
            }
        } else if (temp == 0) return false;

        return false;
    }


    private double incrementAmount(String accountNumber, double amountFromQuery) {

        return findByAccountNumber(accountNumber).getAmount() + amountFromQuery;

    }

    private double incrementAmount(long accountNumber, double amountFromQuery) {

        return findByAccountNumber(accountNumber).getAmount() + amountFromQuery;

    }

    public boolean transferBetweenAccounts(String idFromQuery, String accountNumber, AccountDto accountDto) {

        Account accountFromDb = findByAccountNumber(accountNumber);

        Account tempAccount = accountMapper.mapToAccount(accountDto);

        Account accountForTransfer = findByAccountNumber(tempAccount.getAccountNumber());

        if (accountForTransfer != null && accountForTransfer.getOwner() != null && tempAccount.getAmount() > 0) {
            if (userService.findUserById(idFromQuery) != null && accountFromDb != null && findByAccountNumber(accountNumber).getAmount() >= tempAccount.getAmount()) {
                accountFromDb.setAmount(incrementAmount(accountNumber, tempAccount.getAmount() * (-1)));
                accountRepo.save(accountFromDb);
                accountForTransfer.setAmount(incrementAmount(accountForTransfer.getAccountNumber(), tempAccount.getAmount()));
                accountRepo.save(accountForTransfer);
                return true;
            }
        }
        return false;
    }



}

