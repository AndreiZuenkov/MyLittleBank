package com.example.mylittlebank.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private double amount;

    @Column(name="date_time")
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;


    public Transaction() {
    }

    public Transaction(TransactionType type, double amount, LocalDateTime dateTime, Account account) {
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
        this.account = account;
    }
}
