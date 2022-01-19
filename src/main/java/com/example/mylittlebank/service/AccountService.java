package com.example.mylittlebank.service;

import com.example.mylittlebank.persistence.model.Account;
import com.example.mylittlebank.persistence.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private UserService userService;

    public List<Account> findAllUserAccounts(String idFromQuery) {

        if (idFromQuery != null && !idFromQuery.isEmpty()) {

            List<Account> accountList = accountRepo.findAllByOwner(userService.findUserById(idFromQuery));

            return accountList;
        }
        return null;
    }

    public boolean checkUserAccount(String idFromQuery, String accountNumberFromQuery) {

        List<Account> accountList = findAllUserAccounts(idFromQuery);

        if (accountList != null && !accountList.isEmpty() && accountNumberFromQuery != null && !accountNumberFromQuery.isEmpty()) {

            for (Account account : accountList
            ) {
                if (account.getAccountNumber() == Long.parseLong(accountNumberFromQuery)) {
                    return true;
                }
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

        if (accountNumber != null && !accountNumber.isEmpty()) {
            return accountRepo.findByAccountNumber(Long.parseLong(accountNumber));
        }
        return null;
    }

    public Account findByAccountNumber(long accountNumber) {

        return accountRepo.findByAccountNumber(accountNumber);
    }

    public boolean deleteAccount(String idFromQuery, String accountNumber) {

        if (idFromQuery != null && !idFromQuery.isEmpty() && accountNumber != null && !accountNumber.isEmpty() && checkUserAccount(idFromQuery, accountNumber)) {
            if (findByAccountNumber(accountNumber) != null) {
                accountRepo.delete(findByAccountNumber(accountNumber));
                return true;
            }
        }
        return false;
    }

    public void changeAmount(Account account, double amount) {

        if (account != null && amount != 0) {
            account.setAmount(account.getAmount() + amount);
            accountRepo.save(account);
        }
    }


}

