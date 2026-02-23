package com.example.walletApp.controller;

import com.example.walletApp.dto.*;
import com.example.walletApp.model.Wallet;
import com.example.walletApp.service.WalletService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wallets")
@CrossOrigin(origins = "*")
@Tag(name = "Wallet Controller", description = "APIs for Wallet Management System - Developed by Raj Kumar")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Operation(summary = "Create a new wallet", description = "Adds a new wallet record with an initial balance to the database")
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Wallet create(@Valid @RequestBody Wallet wallet) {
        return walletService.createWallet(wallet);
    }

    @Operation(summary = "Get all wallets", description = "Retrieves the full list of registered wallets and their current balances")
    @GetMapping("/all")
    public List<Wallet> listAll() {
        return walletService.getAllWallets();
    }

    @Operation(summary = "Load money", description = "Increases the balance of a specific wallet using its ID")
    @PutMapping("/load")
    public Wallet load(@RequestBody LoadRequest request) {
        return walletService.loadMoney(request);
    }

    @Operation(summary = "Withdraw money", description = "Decreases the balance of a specific wallet. Validates for insufficient funds.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "400", description = "Insufficient balance or invalid request")
    })
    @PutMapping("/withdraw")
    public Wallet withdraw(@Valid @RequestBody WithdrawRequest request) {
        return walletService.withdrawMoney(request);
    }

    @Operation(summary = "Transfer funds", description = "Transfer money from one mobile number to another. Updates both balances.")
    @PostMapping("/transfer")
    public String transfer(@Valid @RequestBody TransferRequest request) {
        walletService.transferMoney(request);
        return "Transfer successful!";
    }
    
    @Operation(summary = "Delete a wallet", description = "Permanently removes a wallet record from the system using its ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        walletService.deleteWallet(id); 
        return ResponseEntity.ok("Wallet deleted successfully");
    }
}