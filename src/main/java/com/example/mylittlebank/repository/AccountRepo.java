package com.example.mylittlebank.repository;

import com.example.mylittlebank.model.Account;
import com.example.mylittlebank.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountRepo extends CrudRepository<Account, Long> {

    List<Account> findAllByOwner(User user);
}
