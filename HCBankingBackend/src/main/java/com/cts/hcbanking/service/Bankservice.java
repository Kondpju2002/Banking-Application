	package com.cts.hcbanking.service;
	
	import java.sql.Timestamp;
	import java.util.ArrayList;
	import java.util.Date;
	import java.util.List;
	import java.util.Optional;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	
	import com.cts.hcbanking.dao.CustDetailsDao;
import com.cts.hcbanking.dto.CustomerDetailsDTO;
import com.cts.hcbanking.model.CustomerDetails;
	
	@Service
	public class Bankservice {
	
		@Autowired
		private CustDetailsDao custdetailsdao;
	
		public CustomerDetailsDTO addcustomer(CustomerDetailsDTO customerDetailsDTO) {
			
			CustomerDetails customerDetails = new CustomerDetails();
	        customerDetails.setName(customerDetailsDTO.getName());
	        customerDetails.setPhonenum(customerDetailsDTO.getPhonenum());
	        customerDetails.setEmail(customerDetailsDTO.getEmail());
	        customerDetails.setBalance(customerDetailsDTO.getBalance());

	        CustomerDetails savedCustomer = custdetailsdao.save(customerDetails);
	        return new CustomerDetailsDTO( savedCustomer.getName(), savedCustomer.getPhonenum(), savedCustomer.getEmail(), savedCustomer.getBalance());
			
			
	
		}
	
		public List<CustomerDetails> getallCustomers() {
			return custdetailsdao.findAll();
		}
	
		public Optional<CustomerDetails> getCustomerById(long id) {
	
			return custdetailsdao.findById(id);
	
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
					.orElseThrow(() -> new RuntimeException("ACCOUNT NOT FOUND"));
			CustomerDetails custDetails2 = custdetailsdao.findById(receiverId)
					.orElseThrow(() -> new RuntimeException("ACCOUNT NOT FOUND"));
			
			
	
			// check whether we have sufficient balance
			if (custDetails1.getBalance() < amount) {
				throw new RuntimeException("INSUFFICIENT FUNDS in your Account");
			}
			
			double newBalance1 = custDetails1.getBalance() - amount;
			double newBalance2 = custDetails2.getBalance() + amount;
			
			custDetails1.setBalance(newBalance1);
			custDetails2.setBalance(newBalance2);
			ArrayList<CustomerDetails> list=new ArrayList<>();
			custdetailsdao.save(custDetails1);
			custdetailsdao.save(custDetails2);
			list.add(custDetails1);
			list.add(custDetails2);
			return list;
			
			
			
		}
	
		public void deleteAccountById(long id) {
			CustomerDetails custDetails = custdetailsdao.findById(id)
					.orElseThrow(() -> new RuntimeException("ACCOUNT NOT FOUND"));
	
			custdetailsdao.deleteById(id);
		}
	
	
	}
