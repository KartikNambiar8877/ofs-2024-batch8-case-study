package com.ofss.main.service;

import com.ofss.main.domain.Transaction;
import com.ofss.main.domain.Account;
import com.ofss.main.repository.AccountRepository;
import com.ofss.main.repository.TransactionRepository;
import com.ofss.main.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @Transactional
    public boolean deposit(long accountId, double amount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account != null) {
            if ("Current".equalsIgnoreCase(account.getAccountType())) {
                double overdraftCap = 50000.00;
                if (account.getOverdraftBalance() < overdraftCap) {
                    double amountToOverdraft = overdraftCap - account.getOverdraftBalance();
                    if (amount <= amountToOverdraft) {
                        account.setOverdraftBalance(account.getOverdraftBalance() + amount);
                    } else {
                        account.setOverdraftBalance(overdraftCap);
                        account.setMainBalance(account.getMainBalance() + (amount - amountToOverdraft));
                    }
                } else {
                    account.setMainBalance(account.getMainBalance() + amount);
                }
            } else {
                account.setMainBalance(account.getMainBalance() + amount);
            }

            accountRepository.save(account);

            // Only create a transaction if this is not part of a transfer
            if (!Thread.currentThread().getStackTrace()[2].getMethodName().equals("transfer")) {
                Transaction transaction = new Transaction();
                transaction.setSenderAccount(account);
                transaction.setReceiverAccount(account); // Since it's a deposit, the receiver is the same account
                transaction.setTransactionAmount(amount);
                transaction.setTransactionType("DEPOSIT");
                transaction.setTransactionDatetime(Timestamp.valueOf(LocalDateTime.now()));
                transactionRepository.save(transaction);
            }

            return true;
        }
        return false;
    }


    @Override
    @Transactional
    public boolean withdraw(long accountId, double amount) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            return false;
        }

        if ("Savings".equalsIgnoreCase(account.getAccountType())) {
            double minBalance = 10000.00; 
            if (account.getMainBalance() - amount < minBalance) {
                return false;
            } else {
                account.setMainBalance(account.getMainBalance() - amount);
            }
        } else if ("Current".equalsIgnoreCase(account.getAccountType())) {
            if (account.getMainBalance() + account.getOverdraftBalance() < amount) {
                return false;
            }

            if (account.getMainBalance() < amount) {
                account.setOverdraftBalance(account.getOverdraftBalance() - (amount - account.getMainBalance()));
                account.setMainBalance(0);
            } else {
                account.setMainBalance(account.getMainBalance() - amount);
            }
        }

        accountRepository.save(account);

        // Only create a transaction if this is not part of a transfer
        if (!Thread.currentThread().getStackTrace()[2].getMethodName().equals("transfer")) {
            Transaction transaction = new Transaction();
            transaction.setSenderAccount(account);
            transaction.setReceiverAccount(account); // Since it's a withdrawal, the receiver is the same account
            transaction.setTransactionAmount(amount);
            transaction.setTransactionType("Withdraw");
            transaction.setTransactionDatetime(Timestamp.valueOf(LocalDateTime.now()));
            transactionRepository.save(transaction);
        }

        return true;
    }



    @Override
    @Transactional
    public boolean transfer(long senderAccountId, long receiverAccountId, double amount) {
        Account senderAccount = accountRepository.findById(senderAccountId).orElse(null);
        Account receiverAccount = accountRepository.findById(receiverAccountId).orElse(null);
        
        if (senderAccount == null || receiverAccount == null) {
            return false;
        }

        // Perform the withdrawal from the sender's account
        if (!withdraw(senderAccountId, amount)) {
            return false;
        }

        // Perform the deposit into the receiver's account
        if (!deposit(receiverAccountId, amount)) {
            // Roll back the withdrawal if deposit fails
            if ("Savings".equalsIgnoreCase(senderAccount.getAccountType())) {
                senderAccount.setMainBalance(senderAccount.getMainBalance() + amount);
            } else if ("Current".equalsIgnoreCase(senderAccount.getAccountType())) {
                if (senderAccount.getOverdraftBalance() < amount) {
                    senderAccount.setMainBalance(senderAccount.getMainBalance() + amount - senderAccount.getOverdraftBalance());
                    senderAccount.setOverdraftBalance(0);
                } else {
                    senderAccount.setOverdraftBalance(senderAccount.getOverdraftBalance() + amount);
                }
            }
            accountRepository.save(senderAccount);
            return false;
        }

        // Create a single transaction record for the transfer
        Transaction transaction = new Transaction();
        transaction.setSenderAccount(senderAccount);
        transaction.setReceiverAccount(receiverAccount);
        transaction.setTransactionAmount(amount);
        transaction.setTransactionType("Transfer");
        transaction.setTransactionDatetime(Timestamp.valueOf(LocalDateTime.now()));
        transactionRepository.save(transaction);

        return true;
    }



	@Override
	public Optional<Account> getAccountDetails(long accountId) {
		return accountRepository.findById(accountId);
	}

	@Override
	public List<Transaction> getTransactionHistory(long accountNo) {
		return transactionRepository.findByAccountNo(accountNo);
	}

	@Override
	public boolean deleteTransactionbyAccountNo(int accountNo) {
	    try {
	        transactionRepository.deleteTransactionbyAccountNo(accountNo);
	        return true;
	    } catch (Exception e) {
	        // Log the exception
	        return false;
	    }
	}


}
