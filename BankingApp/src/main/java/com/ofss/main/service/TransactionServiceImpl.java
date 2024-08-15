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
        Account account = accountRepository.findById((long)accountId).orElse(null);
        if (account != null) {
        	
        	//System.out.println(account.getMainBalance() + amount);
        	
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

            
            Transaction transaction = new Transaction();
            //System.out.println(accountRepository.findById(accountId));
            transaction.setSenderAccount(accountRepository.findById(accountId).orElse(null));
            transaction.setReceiverAccount(accountRepository.findById(accountId).orElse(null));
            transaction.setTransactionAmount(amount);
            transaction.setTransactionType("DEPOSIT");
            LocalDateTime localdatetime = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(localdatetime);
            transaction.setTransactionDatetime(timestamp);
            transactionRepository.save(transaction);

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

        Transaction transaction = new Transaction();
        transaction.setSenderAccount(accountRepository.findById(accountId).orElse(null));
        transaction.setReceiverAccount(accountRepository.findById(accountId).orElse(null));
        transaction.setTransactionAmount(amount);
        transaction.setTransactionType("Withdraw");
        LocalDateTime localdatetime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localdatetime);
        transaction.setTransactionDatetime(timestamp);
        transactionRepository.save(transaction);

        return true;
    }


    @Override
    @Transactional
    public boolean transfer(long senderAccountId, long receiverAccountId, double amount) {
        if (!withdraw(senderAccountId, amount)) {
            return false;
        }

        if (!deposit(receiverAccountId, amount)) {
            Account senderAccount = accountRepository.findById(senderAccountId).orElse(null);
            if (senderAccount != null) {
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
            }
            return false;
        }

        Transaction transaction = new Transaction();
        transaction.setSenderAccount(accountRepository.findById(senderAccountId).orElse(null));
        transaction.setReceiverAccount(accountRepository.findById(receiverAccountId).orElse(null));
        transaction.setTransactionAmount(amount);
        transaction.setTransactionType("Transfer");
        LocalDateTime localdatetime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localdatetime);
        transaction.setTransactionDatetime(timestamp);
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

}
