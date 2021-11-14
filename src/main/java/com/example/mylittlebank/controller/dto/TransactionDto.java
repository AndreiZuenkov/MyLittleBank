package com.example.mylittlebank.controller.dto;

import com.example.mylittlebank.persistence.model.TransactionType;

import java.time.LocalDateTime;

public class TransactionDto {

    private long id;
    private TransactionType type;
    private double amount;
    private LocalDateTime dateTime;
    private long accountNumber;


    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
