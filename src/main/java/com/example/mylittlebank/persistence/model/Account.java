package com.example.mylittlebank.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
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
}