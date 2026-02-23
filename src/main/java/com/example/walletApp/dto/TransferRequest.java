package com.example.walletApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TransferRequest {
	
    @NotBlank(message = "Sender mobile is required")
    private String fromMobile; 
    
    @NotBlank(message = "Receiver mobile is required")
    private String toMobile; 
    
    @Positive(message = "Transfer amount must be positive")
    private Double amount;
}