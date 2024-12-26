	package com.cts.hcbanking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class CustomerDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Long phonenum;
	private String Email;
	private double balance;
	
	
	
	
//	public CustomerDetails() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

//	public CustomerDetails(Long custId, String name, Long phonenum, String email, double balance) {
//		super();
//		this.id = custId;
//		this.name = name;
//		this.phonenum = phonenum;
//		Email = email;
//		this.balance = balance;
//	}
//	
//	public Long getCustId() {
//		return id;
//	}
//	public void setCustId(Long custId) {
//		this.id = custId;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public Long getPhonenum() {
//		return phonenum;
//	}
//	public void setPhonenum(Long phonenum) {
//		this.phonenum = phonenum;
//	}
//	public String getEmail() {
//		return Email;
//	}
//	public void setEmail(String email) {
//		Email = email;
//	}
//	public double getBalance() {
//		return balance;
//	}
//	public void setBalance(double balance) {
//		this.balance = balance;
//	}
//	
//	

}
