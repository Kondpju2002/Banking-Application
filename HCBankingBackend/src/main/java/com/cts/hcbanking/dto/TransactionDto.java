package com.cts.hcbanking.dto;

import java.time.LocalDateTime;

import lombok.Data;


@Data
public class TransactionDto {

	
	    private Long accountId;
	    private double amount;
	    private String type; // "DEPOSIT" or "WITHDRAWAL"
	    private LocalDateTime timestamp;
}
