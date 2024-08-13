package com.ofss.main.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofss.main.domain.Account;
import com.ofss.main.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean addAccount(Account account) {
        try {
            switch (account.getAccountType().toLowerCase()) {
                case "savings":
                    account.setMinBalance(BigDecimal.valueOf(10000));
                    account.setOverdraftBalance(BigDecimal.ZERO);
                    break;
                case "current":
                    account.setMinBalance(BigDecimal.ZERO);
                    account.setOverdraftBalance(BigDecimal.valueOf(50000));
                    break;
                case "fd":
                    account.setMinBalance(BigDecimal.ZERO); 
                    account.setOverdraftBalance(BigDecimal.ZERO);
                    break;
                default:
                    System.out.println("Invalid account type");
                    return false;
            }

            
            accountRepository.save(account); 
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAccount(Account account) {
        Optional<Account> existingAccountOpt = accountRepository.findById(account.getAccountNo());
        if (existingAccountOpt.isPresent()) {
            Account existingAccount = existingAccountOpt.get();
            
            
            existingAccount.setAccountType(account.getAccountType());


            accountRepository.save(existingAccount);
            return true;
        } else {
            return false;
        }
    }


	@Override
	public Optional<Account> getAccountByAccountNo(long accountNo) {
		
		Optional<Account> acc = accountRepository.findById(accountNo);
		return acc;
	}


	@Override
	public boolean deleteAccountByAccountNo(Account account) {
	    if (accountRepository.existsById(account.getAccountNo())) { 
	        accountRepository.deleteById(account.getAccountNo());  
	        return true;  
	    }
	    return false;  
	}


	@Override
	public List<Account> getAccountsByCustomerId(int customerID) {
	    return accountRepository.getAccountsByCustomerId(customerID);
	}

}
