package com.ofss.main.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ofss.main.domain.Account;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    
    @Query("Select a from Account a where a.customerID = ?1")
    public List<Account> getAccountsByCustomerId(int customerID);
}
