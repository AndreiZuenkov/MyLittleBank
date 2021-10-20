package com.example.mylittlebank.repository;

import com.example.mylittlebank.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepo extends CrudRepository <Transaction, Long> {
}
