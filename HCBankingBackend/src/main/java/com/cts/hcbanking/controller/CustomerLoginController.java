package com.cts.hcbanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hcbanking.model.CustomerLogin;
import com.cts.hcbanking.service.CustomerLoginService;

@RestController
@RequestMapping
public class CustomerLoginController {

	

	@Autowired
	private CustomerLoginService custLoginService;

	@PostMapping("/signup")
	public CustomerLogin signUp(@RequestBody CustomerLogin customer) {
		
		System.out.println(customer.getPassword());
		return custLoginService.saveUser(customer);
	}
}
