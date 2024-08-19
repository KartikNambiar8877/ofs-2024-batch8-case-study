package com.ofss.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ofss.main.domain.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    @Query("SELECT t FROM Transaction t WHERE t.senderAccount.accountNo = ?1 OR t.receiverAccount.accountNo = ?1")
    List<Transaction> findByAccountNo(long accountNo);
    
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Transaction t WHERE t.senderAccount.accountNo = ?1 OR t.receiverAccount.accountNo = ?1")
    void deleteTransactionbyAccountNo(int accountNo);

}

