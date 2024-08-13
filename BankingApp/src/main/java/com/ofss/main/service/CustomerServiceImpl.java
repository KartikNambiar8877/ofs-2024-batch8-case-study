package com.ofss.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ofss.main.domain.Customer;
import com.ofss.main.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer addNewCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public boolean deleteCustomerByCustomerId(int customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            customerRepository.delete(customerOptional.get());
            return true;
        }
        return false;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer loginCustomer(String username, String password) {
        Customer customer = customerRepository.findbyUsername(username);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        if (!customer.getPassword().equals(password)) {
            customer.setLoginAttempts(customer.getLoginAttempts() + 1);
            customerRepository.save(customer);
            throw new RuntimeException("Invalid password");
        }
        // Reset login attempts on successful login
        customer.setLoginAttempts(0);
        customerRepository.save(customer);
        return customer;
    }


    @Override
	public Customer findbyUsername(String username) {
		Customer customerOptional = customerRepository.findbyUsername(username);
		
					return customerOptional;
	}
}
