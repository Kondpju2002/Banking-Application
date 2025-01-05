package com.cts.hcbanking.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.hcbanking.dao.CustDetailsDao;
import com.cts.hcbanking.exception.ResourceNotFoundException;
import com.cts.hcbanking.model.CustomerDetails;

@Service
public class Bankservice {

	@Autowired
	private CustDetailsDao custdetailsdao;

	public boolean isAccountOwner(Long accountId, String username) {
		CustomerDetails customerDetails = custdetailsdao.findById(accountId)
				.orElseThrow(() -> new RuntimeException("ACCOUNT NOT FOUND"));

		return customerDetails.getName().equals(username);

	}

	public List<CustomerDetails> getallCustomers() {
		return custdetailsdao.findAll();
	}

	public CustomerDetails getCustomerById(long id) {

		CustomerDetails customerDetails = custdetailsdao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer ", id));

		return customerDetails;
	}

	public CustomerDetails deposit(Long id, double amount) {
		CustomerDetails custDetails = custdetailsdao.findById(id)
				.orElseThrow(() -> new RuntimeException("Account not found"));

		// Update the balance
		double newBalance = custDetails.getBalance() + amount;
		custDetails.setBalance(newBalance);

		// Save the updated customer details
		return custdetailsdao.save(custDetails);
	}

	public CustomerDetails withdraw(Long id, double amount) {
		CustomerDetails custDetails = custdetailsdao.findById(id)
				.orElseThrow(() -> new RuntimeException("ACCOUNT NOT FOUND"));

		// check whether we have sufficient balance
		if (custDetails.getBalance() < amount) {
			throw new RuntimeException("INSUFFICIENT FUNDS in your Account");
		}

		// Update the balance
		double newBalance = custDetails.getBalance() - amount;
		custDetails.setBalance(newBalance);

		// Save the updated customer details
		return custdetailsdao.save(custDetails);
	}

	public List<CustomerDetails> transferAmount(Long id, Double amount, Long receiverId) {
		CustomerDetails custDetails1 = custdetailsdao.findById(id)
				.orElseThrow(() -> new RuntimeException("SENDER ACCOUNT NOT FOUND"));
		CustomerDetails custDetails2 = custdetailsdao.findById(receiverId)
				.orElseThrow(() -> new RuntimeException("RECEIVER ACCOUNT NOT FOUND"));

		// check whether we have sufficient balance
		if (custDetails1.getBalance() < amount) {
			throw new RuntimeException("INSUFFICIENT FUNDS in your Account");
		}

		double newBalance1 = custDetails1.getBalance() - amount;
		double newBalance2 = custDetails2.getBalance() + amount;

		custDetails1.setBalance(newBalance1);
		custDetails2.setBalance(newBalance2);

		custdetailsdao.save(custDetails1);
		custdetailsdao.save(custDetails2);
		return Arrays.asList(custDetails1, custDetails2);

	}

	public void deleteAccountById(long id) {
		CustomerDetails custDetails = custdetailsdao.findById(id)
				.orElseThrow(() -> new RuntimeException("ACCOUNT NOT FOUND"));

		custdetailsdao.deleteById(id);
	}

}
