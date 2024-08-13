package com.ofss.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ofss.main.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	@Query("Select c from Customer c where c.username=?1")
	public Customer findbyUsername(String username);
	

}
