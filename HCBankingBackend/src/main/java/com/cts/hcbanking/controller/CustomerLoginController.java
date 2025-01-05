package com.cts.hcbanking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hcbanking.dto.CustomerDetailsDTO;
import com.cts.hcbanking.dto.CustomerLoginDto;
import com.cts.hcbanking.dto.CustomerSignInDto;
import com.cts.hcbanking.model.CustomerDetails;
import com.cts.hcbanking.model.CustomerLogin;
import com.cts.hcbanking.service.CustomerLoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class CustomerLoginController {

	

	@Autowired
	private CustomerLoginService custLoginService;

	@PostMapping("/signup")
	public ResponseEntity<CustomerDetailsDTO> signUp(@RequestBody @Valid CustomerDetailsDTO customerdetails) {
		
//		System.out.println(customerdetails.getPassword());
		return custLoginService.signUp(customerdetails);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> signIn(@RequestBody @Valid CustomerSignInDto customer) {
		
		try {
//            CustomerLogin loggedInCustomer = custLoginService.signIn(customer);
			CustomerDetails loggedInCustomer = custLoginService.signIn(customer);
            return new ResponseEntity<>(loggedInCustomer, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
	}
	
//	+"\nLogged in Successfully!!"
}
