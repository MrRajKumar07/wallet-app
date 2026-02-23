package com.example.walletApp.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LoadRequest { 
	
    @NotNull(message = "ID cannot be null")
    private Long id; 

    @Positive(message = "Amount must be greater than zero")
    private Double amount; 
}
