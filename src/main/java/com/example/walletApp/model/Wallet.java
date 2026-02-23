package com.example.walletApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "wallets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Mobile number is required")
    @Size(min = 10, max = 10, message = "Mobile must be 10 digits")
    @Column(unique = true)
    private String mobile;
    
    private Double balance;
}