package com.ofss.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofss.main.domain.Customer;
import com.ofss.main.service.CustomerService;

@CrossOrigin("*")
@RequestMapping("customer")
@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	@PostMapping("addcustomer")
	public Customer addCustomer(@RequestBody Customer customer) {
		System.out.println();
		System.out.println(customer);
		System.out.println();
		return customerService.addNewCustomer(customer);
	}
	@PostMapping("findbyusername/{username}")
	public Customer findbyUsername(@PathVariable String username) {
		return customerService.findbyUsername(username);
	}
	
	@PostMapping("login")
	public Customer loginCustomer(@RequestBody Customer customer) {
		return customerService.loginCustomer(customer.getUsername(), customer.getPassword());
	}
	@PutMapping("update")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }
	@GetMapping("allcustomers")
    public List<Customer> getallCustomers() {
        return customerService.getallCustomers();
    }
	
}