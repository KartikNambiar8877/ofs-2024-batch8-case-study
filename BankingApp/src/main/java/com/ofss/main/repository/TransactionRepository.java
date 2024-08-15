package com.ofss.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ofss.main.domain.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    @Query("SELECT t FROM Transaction t WHERE t.senderAccount.accountNo = ?1 OR t.receiverAccount.accountNo = ?1")
    List<Transaction> findByAccountNo(long accountNo);
}

