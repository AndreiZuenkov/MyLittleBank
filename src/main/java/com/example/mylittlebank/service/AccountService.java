package com.example.mylittlebank.service;

import com.example.mylittlebank.model.Account;
import com.example.mylittlebank.model.User;
import com.example.mylittlebank.repository.AccountRepo;
import com.example.mylittlebank.repository.UserRepo;
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


        List<Account> accountList=accountRepo.findAllByOwner(userService.findUserById(idFromQuery));

        return accountList;
    }

    public Account createAccount(String idFromQuery, Account accountFromQuery){

        User user=userService.findUserById(idFromQuery);

        Account account=new Account(new Random().nextLong(),
                0,
                LocalDate.now(),
                LocalDate.of(LocalDate.now().getYear()+3, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()),
                user);

        accountRepo.save(account);

        return account;
    }

    private Account findByAccountNumber(String accountNumber){
        return accountRepo.findByAccountNumber(Long.parseLong(accountNumber));
    }

    public boolean deleteAccount(String idFromQuery, String accountNumber){

        if(findByAccountNumber(accountNumber) !=null){
            accountRepo.delete(findByAccountNumber(accountNumber));
            return true;
        }
        return false;
    }
}
