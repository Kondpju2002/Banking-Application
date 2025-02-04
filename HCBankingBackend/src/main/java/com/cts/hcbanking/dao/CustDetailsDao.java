package com.cts.hcbanking.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.hcbanking.model.CustomerDetails;
import com.cts.hcbanking.model.CustomerLogin;

import java.util.List;


@Repository
public interface CustDetailsDao extends JpaRepository<CustomerDetails,Long> {

//	   double findBalanceById(Long id);
	Optional<CustomerDetails> findById(Long id);
	
	CustomerDetails findByName(String name);
	
//	boolean existsByEmail(String email);
	
//	CustomerDetails  findByName(String name);
//
//	void setBalance(double d);


}

