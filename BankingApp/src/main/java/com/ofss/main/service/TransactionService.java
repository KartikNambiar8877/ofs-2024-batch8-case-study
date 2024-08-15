package com.ofss.main.service;

import com.ofss.main.domain.Transaction;
import com.ofss.main.domain.Account;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    boolean deposit(long accountId, double amount);
    boolean withdraw(long accountId, double amount);
    boolean transfer(long senderAccountId, long receiverAccountId, double amount);
    Optional<Account> getAccountDetails(long accountId);
    List<Transaction> getTransactionHistory(long accountNo);
}
