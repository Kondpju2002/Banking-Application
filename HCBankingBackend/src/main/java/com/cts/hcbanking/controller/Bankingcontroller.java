package com.cts.hcbanking.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hcbanking.dto.CustomerDetailsDTO;
import com.cts.hcbanking.model.CustomerDetails;
import com.cts.hcbanking.service.Bankservice;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/Bank")
public class Bankingcontroller {

	@Autowired
	private Bankservice bankService;

	@PostMapping("customers")
	@PreAuthorize("hasAuthority('ADMIN') ")
	public ResponseEntity<CustomerDetailsDTO> addCustomer(@Valid @RequestBody CustomerDetailsDTO customerDetailsDTO) {
		return ResponseEntity.ok(bankService.addcustomer(customerDetailsDTO));
	}

	@GetMapping("customers")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')" )
	public List<CustomerDetails> getallCustomers() {
		return bankService.getallCustomers();

	}

	@GetMapping("/customers/{id}")
	@PreAuthorize("hasAuthority('ADMIN')" )
	public CustomerDetails getCustomerById(@PathVariable long id) {
		return bankService.getCustomerById(id) .orElseThrow(() -> new RuntimeException("ACCOUNT NOT FOUND!"));
//		 return bankService.getCustomerById(id) .orElseThrow( );
	}
	
	
    @PostMapping("/{id}/deposit")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')" )
    public CustomerDetails deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return bankService.deposit(id, amount);
    }
	
    @PostMapping("/{id}/withdraw")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')" )
    public CustomerDetails withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return bankService.withdraw(id, amount);
    }
    
    @PostMapping("/{id}/transfer/{receiverId}")
    public List<CustomerDetails> transferAmount(@PathVariable Long id,@PathVariable Long receiverId, @RequestBody Map<String, Double> request) {
    	Double amount =request.get("amount");
    	return bankService.transferAmount(id,amount,receiverId);
    	
    }
    
    
    @DeleteMapping("{id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')" )
    public void deleteAccount(@PathVariable Long id ) {
    	bankService.deleteAccountById(id);
    }
    
    
}
