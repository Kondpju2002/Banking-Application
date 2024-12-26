package com.cts.hcbanking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.hcbanking.model.CustomerDetails;

@Repository
public interface TransactionDao extends JpaRepository<CustomerDetails,Long>{

}
