package com.example.mylittlebank.service;

import com.example.mylittlebank.controller.dto.TransactionDto;
import com.example.mylittlebank.controller.dto.Operation;
import com.example.mylittlebank.persistence.model.Account;
import com.example.mylittlebank.persistence.model.Transaction;
import com.example.mylittlebank.persistence.model.TransactionType;
import com.example.mylittlebank.persistence.repository.TransactionRepo;
import com.example.mylittlebank.service.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    private final Queue<Operation> queueOfOperation = new LinkedList();


    public void doTransaction(String idFromQuery, String accountNumberFromQuery, TransactionDto transactionDto) {

        queueOfOperation.offer(new Operation(idFromQuery, accountNumberFromQuery, transactionDto));

        checkOperation();

    }


    private void checkOperation() {

        while (!queueOfOperation.isEmpty()) {

            Operation operation = queueOfOperation.poll();

            Transaction transaction = transactionMapper.mapToTransaction(operation.getTransactionDto());

            switch (operation.getTransactionDto().getType()) {
                case TRANSFER:
                    doTransfer(operation.getIdFromQuery(), operation.getAccountNumberFromQuery(), transaction);
                    break;
                case CREDITING:
                    doCrediting(operation.getIdFromQuery(), operation.getAccountNumberFromQuery(), transaction);
                    break;
                case WITHDRAWING:
                    doWithdrawing(operation.getIdFromQuery(), operation.getAccountNumberFromQuery(), transaction);
                    break;
            }

        }


    }

    private boolean doTransfer(String idFromQuery, String accountNumberFromQuery, Transaction transaction) {

        Account accountFrom = accountService.findByAccountNumber(accountNumberFromQuery);
        Account accountTo = accountService.findByAccountNumber(transaction.getAccount().getAccountNumber());

        if (userService.findUserById(idFromQuery) != null && accountFrom != null && accountTo != null
                && accountFrom.getAccountNumber() != accountTo.getAccountNumber()
                && transaction.getAmount() > 0) {
            if (accountFrom.getAmount() >= transaction.getAmount()) {
                accountService.changeAmount(accountFrom, (-transaction.getAmount()));
                transactionRepo.save(new Transaction(transaction.getType(), transaction.getAmount(), LocalDateTime.now(), accountFrom));
                accountService.changeAmount(accountTo, transaction.getAmount());
                transactionRepo.save(new Transaction(TransactionType.CREDITING, transaction.getAmount(), LocalDateTime.now(), accountTo));
                return true;
            }
        }
        return false;
    }


    private boolean doCrediting(String idFromQuery, String accountNumberFromQuery, Transaction transaction) {
        Account account = accountService.findByAccountNumber(accountNumberFromQuery);

        if (userService.findUserById(idFromQuery) != null && accountService.checkUserAccount(idFromQuery, accountNumberFromQuery)
                && account != null && transaction.getAmount() > 0) {
            accountService.changeAmount(account, transaction.getAmount());
            transactionRepo.save(new Transaction(transaction.getType(), transaction.getAmount(), LocalDateTime.now(), account));
            return true;
        }
        return false;
    }

    private boolean doWithdrawing(String idFromQuery, String accountNumberFromQuery, Transaction transaction) {
        Account account = accountService.findByAccountNumber(accountNumberFromQuery);

        if (userService.findUserById(idFromQuery) != null && account != null && transaction.getAmount() > 0) {
            accountService.changeAmount(account, (-transaction.getAmount()));
            transactionRepo.save(new Transaction(transaction.getType(), transaction.getAmount(), LocalDateTime.now(), account));
            return true;
        }
        return false;
    }


}
