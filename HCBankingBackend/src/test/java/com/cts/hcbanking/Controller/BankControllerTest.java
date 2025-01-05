package com.cts.hcbanking.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.cts.hcbanking.controller.Bankingcontroller;
import com.cts.hcbanking.dto.CustomerDetailsDTO;
import com.cts.hcbanking.dto.TransactionRequestDto;
import com.cts.hcbanking.model.CustomerDetails;
import com.cts.hcbanking.service.Bankservice;

@ExtendWith(MockitoExtension.class)
public class BankControllerTest {

	@Mock
	private UserDetails userDetails;

	@Mock
	private Bankservice bankService;

	@Mock
	private SecurityContext securityContext;

	@Mock
	private Authentication authentication;

	@InjectMocks
	private Bankingcontroller bankingcontroller;

	private CustomerDetailsDTO customerDetailsDTO;
	private CustomerDetails customerDetails;

	@BeforeEach
	public void setUp() {
		customerDetailsDTO = new CustomerDetailsDTO("John Doe", 1234567890L, "john@example.com", 1000.0, "John@1234",
				"ADMIN");
		customerDetails = new CustomerDetails(1L, "John Doe", 1234567890L, "john@example.com", 1000.0, "John@1234",
				"ADMIN");

	}

	@Test
	public void testGetAllCustomers() {
		List<CustomerDetails> customers = Arrays.asList(
				new CustomerDetails(1L, "John Doe", 1234567890L, "john@example.com", 1000.0, "John@123", "Admin"),
				new CustomerDetails(2L, "Jane Doe", 90987654321L, "jane@example.com", 2000.0, "jane@123", "ADMIN"));

		when(bankService.getallCustomers()).thenReturn(customers);

		List<CustomerDetails> response = bankingcontroller.getallCustomers();

		assertNotNull(response);
		assertEquals(2, response.size());
		assertEquals(customers, response);
	}

	@Test
	public void testGetCustomerById() {
		when(bankService.getCustomerById(1L)).thenReturn(customerDetails);

		CustomerDetails response = bankingcontroller.getCustomerById(1L);

		assertNotNull(response);
		assertEquals(customerDetails, response);
	}

	@Test
	public void testDeposit() {
		Map<String, Double> request = Map.of("amount", 1000.0);
		when(bankService.deposit(1L, 1000.0)).thenReturn(customerDetails);

		ResponseEntity<CustomerDetails> response = bankingcontroller.deposit(1L, request);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(customerDetails, response.getBody());
	}

	@Test
	public void testWithdraw() {

		Long accountId = 1L;
		Double amount = 500.0;
		Map<String, Double> request = Map.of("amount", amount);
		CustomerDetails customerDetails = new CustomerDetails();
		customerDetails.setBalance(1500.0);
		SecurityContextHolder.setContext(securityContext);
		when(securityContext.getAuthentication()).thenReturn(authentication);

		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getPrincipal()).thenReturn(userDetails);
		when(userDetails.getUsername()).thenReturn("user1");
		when(bankService.isAccountOwner(accountId, "user1")).thenReturn(true);
		when(bankService.withdraw(accountId, amount)).thenReturn(customerDetails);

		ResponseEntity<String> response = bankingcontroller.withdraw(accountId, request);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("The Remaining Balance is 1500.0", response.getBody());
	}

	@Test
	public void testTransferAmount() {
		CustomerDetails sender = new CustomerDetails(1L, "John Doe", 1234567890L, "john@example.com", 500.0, "John@123",
				"ADMIN");
		CustomerDetails receiver = new CustomerDetails(2L, "Jane Doe", 90987654320L, "jane@example.com", 1500.0,
				"Jane@123", "ADMIN");
		List<CustomerDetails> updatedCustomers = Arrays.asList(sender, receiver);
		TransactionRequestDto request = new TransactionRequestDto(500.0);

		when(bankService.transferAmount(1L, 500.0, 2L)).thenReturn(updatedCustomers);

		ResponseEntity<?> response = bankingcontroller.transferAmount(1L, 2L, request);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(updatedCustomers, response.getBody());
	}

	@Test
	public void testDeleteAccount() {
		bankingcontroller.deleteAccount(1L);

		verify(bankService).deleteAccountById(1L);
	}
}