package com.example.mylittlebank.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "account_number")
    private long accountNumber;

    private double amount;

    @Column(name = "opening_date")
    private LocalDate openingDate;

    @Column(name = "validity_period")
    private LocalDate validityPeriod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    public Account() {
    }

    public Account(long accountNumber, double amount, LocalDate openingDate, LocalDate validityPeriod, User owner) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.openingDate = openingDate;
        this.validityPeriod = validityPeriod;
        this.owner = owner;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDate getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(LocalDate validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
