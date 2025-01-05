package com.cts.hcbanking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class CustomerLoginDto {

		
//		public String username;
////		public String email;
//		public String password;
////		public String confirmPassword;
//		public String role;
		
		@NotBlank(message = "Username is mandatory")
	    @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
	    private String username;

	    @NotBlank(message = "Password is mandatory")
	    @Size(min = 8, message = "Password must be at least 6 characters long")
	    private String password;

	    @NotBlank(message = "Role is mandatory")
	    private String role;
		
}
