package com.example.mylittlebank.repository;

import com.example.mylittlebank.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends CrudRepository<Account, Long> {
}
