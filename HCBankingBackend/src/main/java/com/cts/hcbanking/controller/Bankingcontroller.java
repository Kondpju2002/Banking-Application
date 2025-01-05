package com.cts.hcbanking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.hcbanking.dto.TransactionRequestDto;
import com.cts.hcbanking.model.CustomerDetails;
import com.cts.hcbanking.service.Bankservice;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/Bank")
public class Bankingcontroller {

	@Autowired
	private Bankservice bankService;

	@GetMapping("customers")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public List<CustomerDetails> getallCustomers() {
		return bankService.getallCustomers();

	}

	@GetMapping("/customers/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public CustomerDetails getCustomerById(@PathVariable long id) {
		return bankService.getCustomerById(id);
	}

	@PostMapping("/{id}/deposit")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<CustomerDetails> deposit(@PathVariable Long id,
			@Valid @RequestBody Map<String, Double> request) {
		Double amount = request.get("amount");
		CustomerDetails updatedCustomer = bankService.deposit(id, amount);
		return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);

	}

	@PostMapping("/{id}/withdraw")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
//	public ResponseEntity<CustomerDetails> withdraw(@PathVariable Long id,
	public ResponseEntity<String> withdraw(@PathVariable Long id, @RequestBody @Valid Map<String, Double> request) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = ((UserDetails) authentication.getPrincipal()).getUsername();

		// Verify the account ownership
		if (!bankService.isAccountOwner(id, username)) {
			return new ResponseEntity<>("You are not authorized to perform this action on this account.",
					HttpStatus.FORBIDDEN);
		}

		Double amount = request.get("amount");
		CustomerDetails updatedCustomer = bankService.withdraw(id, amount);
		return new ResponseEntity<>("The Remaining Balance is " + String.valueOf(updatedCustomer.getBalance()),
				HttpStatus.OK);
	}

	@PostMapping("/{id}/transfer/{receiverId}")
	public ResponseEntity<?> transferAmount(@PathVariable Long id, @PathVariable Long receiverId,
			@RequestBody @Valid TransactionRequestDto request) {

		try {
			List<CustomerDetails> updatedCustomer = bankService.transferAmount(id, request.getAmount(), receiverId);
			return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

	@DeleteMapping("{id}/delete")
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteAccount(@PathVariable Long id) {
		bankService.deleteAccountById(id);
	}

}
