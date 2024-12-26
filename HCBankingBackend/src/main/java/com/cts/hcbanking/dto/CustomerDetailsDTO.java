package com.cts.hcbanking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class CustomerDetailsDTO {

	@NotBlank(message = "Name is mandatory")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;
	@NotNull(message = "Phone number is mandatory")
    @Min(value = 1000000000L, message = "Phone number should be at least 10 digits")
	private Long phonenum;
	@NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
	private String Email;
	@Min(value = 0, message = "Balance must be non-negative")
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
