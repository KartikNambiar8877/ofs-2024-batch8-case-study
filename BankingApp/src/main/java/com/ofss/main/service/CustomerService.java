package com.ofss.main.service;

import com.ofss.main.domain.Customer;

public interface CustomerService {

    public Customer findbyUsername(String username);

    public Customer addNewCustomer(Customer customer);

    public Customer updateCustomer(Customer customer);

    public boolean deleteCustomerByCustomerId(int customerId);
    
    public Customer loginCustomer(String username,String password);
	

}
