package com.cts.hcbanking.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public class TransactionRequestDto {

    @NotNull(message = "Amount is mandatory")
    @Min(value = 1, message = "Amount must be greater than zero")
    private double amount;

    // Getters and Setters
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}


