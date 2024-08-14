package com.ofss.main.service;

import com.ofss.main.domain.Transaction;
import com.ofss.main.domain.Account;
import com.ofss.main.repository.AccountRepository;
import com.ofss.main.repository.TransactionRepository;
import com.ofss.main.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @Transactional
    public boolean deposit(long accountId, double amount) {
        Account account = accountRepository.findById((long)accountId).orElse(null);
        if (account != null) {
        	
        	System.out.println(account.getMainBalance() + amount);
            account.setMainBalance(account.getMainBalance() + amount);
            accountRepository.save(account);

            
            Transaction transaction = new Transaction();
            transaction.setSenderAccount(accountRepository.findById(accountId).orElse(null));
            transaction.setTransactionAmount(amount);
            transaction.setTransactionType("DEPOSIT");
            transactionRepository.save(transaction);

            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean withdraw(long accountId, double amount) {
        Account account = accountRepository.findById((long)accountId).orElse(null);
        if (account != null && account.getMainBalance() >= amount) {
            account.setMainBalance(account.getMainBalance() - amount);
            accountRepository.save(account);

            
            Transaction transaction = new Transaction();
            transaction.setSenderAccount(accountRepository.findById(accountId).orElse(null));
            transaction.setTransactionAmount(amount);
            transaction.setTransactionType("WITHDRAWAL");
            transactionRepository.save(transaction);

            return true;
        }
        return false;
    }

	@Override
	public boolean transfer(long senderAccountId, long receiverAccountId, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account getAccountDetails(long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getTransactionHistory(long accountNo) {
		// TODO Auto-generated method stub
		return null;
	}

}
