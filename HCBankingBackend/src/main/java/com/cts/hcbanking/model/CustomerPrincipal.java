package com.cts.hcbanking.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomerPrincipal implements UserDetails {

//	private CustomerLogin login;
	private CustomerDetails login;

	public CustomerPrincipal(CustomerDetails user) {
		this.login = user;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority(login.getRole()));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return login.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
//		return login.getUsername();
		return login.getName();
	}

}
