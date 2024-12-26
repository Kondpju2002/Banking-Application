package com.cts.hcbanking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.hcbanking.model.CustomerLogin;

@Repository
public interface CustomerLoginRepo extends JpaRepository<CustomerLogin,Integer>{

	CustomerLogin findByUsername(String username);
}
