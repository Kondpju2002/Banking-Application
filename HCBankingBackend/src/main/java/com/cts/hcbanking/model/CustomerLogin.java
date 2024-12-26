package com.cts.hcbanking.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class CustomerLogin {

		@Id
		public int id;
		public String username;
//		public String email;
		public String password;
//		public String confirmPassword;
		public String role;
		
}
