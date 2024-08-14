package com.ofss.main.controller;


import com.ofss.main.domain.Transaction;
import com.ofss.main.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public boolean deposit(@RequestBody Transaction transaction) {
        return transactionService.deposit(transaction.getSenderAccount().getAccountNo(), transaction.getTransactionAmount());
    }

    @PostMapping("/withdraw")
    public boolean withdraw(@RequestBody Transaction transaction) {
        return transactionService.withdraw(transaction.getSenderAccount().getAccountNo(), transaction.getTransactionAmount());
    }
}

