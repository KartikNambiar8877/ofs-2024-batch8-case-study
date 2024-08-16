package com.ofss.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofss.main.domain.Account;
import com.ofss.main.service.AccountService;

@CrossOrigin("*")
@RequestMapping("account")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("addaccount")
    public String addAccount(@RequestBody Account account) {
        boolean result = accountService.addAccount(account);
        if (result) {
            return "Account added successfully";
        } else {
            return "Failed to add account";
        }
    }
    
    @PostMapping("accountdetails")
    public Optional<Account> getAccountByAccountNo(@RequestBody Account account) {
        return accountService.getAccountByAccountNo(account.getAccountNo());
    }
    
    @PostMapping("delete")
    public boolean deleteAccountByAccountNo(@RequestBody Account account) {
        boolean result = accountService.deleteAccountByAccountNo(account);
        return result;
    }
    
    @PostMapping("listaccounts/{id}")
    public List<Account> listAccountsByCustomerId(@PathVariable("id") int customerId) {
        return accountService.getAccountsByCustomerId(customerId);
    }
    @PutMapping("update")
    public String updateAccount(@RequestBody Account account) {
        boolean result = accountService.updateAccount(account);
        if (result) {
            return "Account updated successfully";
        } else {
            return "Failed to update account";
        }
    }

}
