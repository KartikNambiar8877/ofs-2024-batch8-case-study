package com.ofss.main.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofss.main.domain.Account;
import com.ofss.main.service.AccountService;
import com.ofss.main.service.ChequeService;
import com.ofss.main.service.RoiService;
import com.ofss.main.service.TransactionServiceImpl;

@CrossOrigin("*")
@RequestMapping("account")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private ChequeService chequeService;
    
    @Autowired
    private RoiService roiService;
    
    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @PostMapping("addaccount")
    public boolean addAccount(@RequestBody Account account) {
        boolean result = accountService.addAccount(account);
        if (result) {
            return true;
        } else {
            return false;
        }
    }
    
    @PostMapping("accountdetails")
    public Optional<Account> getAccountByAccountNo(@RequestBody Account account) {
        return accountService.getAccountByAccountNo(account.getAccountNo());
    }
    
    @PostMapping("delete")
    public boolean deleteAccountByAccountNo(@RequestBody Account account) {
        int accountNo = (int) account.getAccountNo();

        roiService.deletebyAccountNo(accountNo);
        transactionServiceImpl.deleteTransactionbyAccountNo(accountNo);
        chequeService.deleteChequesbyAccountNo(accountNo);
        boolean result = accountService.deleteAccountByAccountNo(account);
        
        return result;
    }

    
    @GetMapping("listaccounts/{id}")
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
