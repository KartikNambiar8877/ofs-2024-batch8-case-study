package com.ofss.main.controller;


import com.ofss.main.domain.Account;
import com.ofss.main.domain.Transaction;
import com.ofss.main.service.AccountService;
import com.ofss.main.service.TransactionService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;

    @PostMapping("/deposit")
    public boolean deposit(@RequestBody Transaction transaction) {
        return transactionService.deposit(transaction.getSenderAccount().getAccountNo(), transaction.getTransactionAmount());
    }

    @PostMapping("/withdraw")
    public boolean withdraw(@RequestBody Transaction transaction) {
        return transactionService.withdraw(transaction.getSenderAccount().getAccountNo(), transaction.getTransactionAmount());
    }
    
    @PostMapping("/transfer")
    public boolean transfer(@RequestBody Transaction transaction) {
        return transactionService.transfer(transaction.getSenderAccount().getAccountNo(),transaction.getReceiverAccount().getAccountNo(),transaction.getTransactionAmount());
    }
    
    @PostMapping("/balancedetails")
    public Optional<Account> balancedetails(@RequestBody Account account) {
        return accountService.getAccountByAccountNo(account.getAccountNo());
    }
    
    @PostMapping("/transactionhistory")
    public List<Transaction> transactionhistory(@RequestBody Account account) {
        return transactionService.getTransactionHistory(account.getAccountNo());
    }
    @DeleteMapping("/delete/{accountNo}")
    public boolean deleteTransactionbyAccountNo(@PathVariable int accountNo) {
    	try {
            transactionService.deleteTransactionbyAccountNo(accountNo);
            return true;  
        } catch (Exception e) {
        	System.out.println(e);
            return false;
        }
    }
}

