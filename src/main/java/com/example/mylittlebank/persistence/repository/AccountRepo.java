package com.example.mylittlebank.persistence.repository;

import com.example.mylittlebank.persistence.model.Account;
import com.example.mylittlebank.persistence.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepo extends CrudRepository<Account, Long> {

    List<Account> findAllByOwner(User user);
    Account findByAccountNumber(long accountNumber);
}
