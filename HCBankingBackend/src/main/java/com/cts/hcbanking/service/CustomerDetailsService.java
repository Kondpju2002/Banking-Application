package com.cts.hcbanking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.hcbanking.dao.CustDetailsDao;
import com.cts.hcbanking.dao.CustomerLoginRepo;
import com.cts.hcbanking.model.CustomerDetails;
import com.cts.hcbanking.model.CustomerLogin;
import com.cts.hcbanking.model.CustomerPrincipal;

@Service
public class CustomerDetailsService implements UserDetailsService {

	@Autowired
//	private CustomerLoginRepo repo;
	private CustDetailsDao repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//		System.out.println(username);

//		CustomerLogin customer = repo.findByUsername(username);
		CustomerDetails customer = repo.findByName(username);
		
//	    System.out.println(user);

		if (customer == null) {
			System.out.println("User 404");
			throw new UsernameNotFoundException("User 404");
		}

		return new CustomerPrincipal(customer);
	}

}
