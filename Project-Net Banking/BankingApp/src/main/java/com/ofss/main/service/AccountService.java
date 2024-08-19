package com.ofss.main.service;

import java.util.List;
import java.util.Optional;

import com.ofss.main.domain.Account;

public interface AccountService {
     boolean addAccount(Account account);
     Optional<Account> getAccountByAccountNo(long accountNo);
     boolean deleteAccountByAccountNo(Account account);
	 boolean updateAccount(Account account);
	 List<Account> getAccountsByCustomerId(int customerID);
}