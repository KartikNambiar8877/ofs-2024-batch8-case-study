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
    	Integer customerId = (int) customer.getCustomerId(); // Ensure this cast is safe
        Optional<Customer> existingCustomerOpt = customerRepository.findById(customerId);
        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();
           
            existingCustomer.setfName(customer.getfName());
            existingCustomer.setlName(customer.getlName());
            existingCustomer.setUsername(customer.getUsername());
            existingCustomer.setPassword(customer.getPassword());
            existingCustomer.setAddressLine1(customer.getAddressLine1());
            existingCustomer.setAddressLine2(customer.getAddressLine2());
            existingCustomer.setAddressLine3(customer.getAddressLine3());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setEmail(customer.getEmail());

            return customerRepository.save(existingCustomer);
        } else {
            throw new RuntimeException("Customer not found");
        }
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
