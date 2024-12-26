package com.cts.hcbanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.hcbanking.dao.CustomerLoginRepo;
import com.cts.hcbanking.model.CustomerLogin;

@Service
public class CustomerLoginService {

	@Autowired
	private CustomerLoginRepo repo; 
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public CustomerLogin saveUser(CustomerLogin customer) {
		customer.setPassword(encoder.encode(customer.getPassword()));
		System.out.println("Welcome");
		return repo.save(customer);
	}
}
