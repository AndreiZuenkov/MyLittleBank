package com.example.mylittlebank.persistence.repository;

import com.example.mylittlebank.persistence.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepo extends CrudRepository <Transaction, Long> {
}
