package com.cts.hcbanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.hcbanking.dao.CustDetailsDao;
import com.cts.hcbanking.dao.CustomerLoginRepo;
import com.cts.hcbanking.dto.CustomerDetailsDTO;
import com.cts.hcbanking.dto.CustomerLoginDto;
import com.cts.hcbanking.dto.CustomerSignInDto;
import com.cts.hcbanking.model.CustomerDetails;
import com.cts.hcbanking.model.CustomerLogin;

import jakarta.validation.Valid;

@Service
public class CustomerLoginService {

	@Autowired
	private CustomerLoginRepo customerLogindao;

	@Autowired
	private CustDetailsDao customerDetailsDao;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public ResponseEntity<CustomerDetailsDTO> signUp(CustomerDetailsDTO customerDto) {

		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setName(customerDto.getName());
		customerDetails.setPhonenum(customerDto.getPhonenum());
		customerDetails.setEmail(customerDto.getEmail());
		customerDetails.setBalance(customerDto.getBalance());
		customerDetails.setPassword(new BCryptPasswordEncoder().encode(customerDto.getPassword()));
		customerDetails.setRole(customerDto.getRole());

		CustomerDetails savedCustomer = customerDetailsDao.save(customerDetails);

		CustomerDetailsDTO saveCustomerDto = new CustomerDetailsDTO(savedCustomer.getName(),
				savedCustomer.getPhonenum(), savedCustomer.getEmail(), savedCustomer.getBalance(),
				savedCustomer.getPassword(), savedCustomer.getRole());

		return new ResponseEntity<>(saveCustomerDto, HttpStatus.CREATED);
	}

	public CustomerDetails signIn(CustomerSignInDto customer) {

//		CustomerLogin existingCustomer = customerLogindao.findByUsername(customer.getUsername());
		CustomerDetails existingCustomer = customerDetailsDao.findByName(customer.getUsername());
		if (existingCustomer != null && encoder.matches(customer.getPassword(), existingCustomer.getPassword())) {
			return existingCustomer;
		} else {
			throw new RuntimeException("Invalid username or password");
		}
	}

}
