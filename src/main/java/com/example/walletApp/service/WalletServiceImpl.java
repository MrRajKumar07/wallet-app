package com.example.walletApp.service;

import com.example.walletApp.dto.*;
import com.example.walletApp.exception.*;
import com.example.walletApp.model.Wallet;
import com.example.walletApp.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository repository;

    @Override
    public Wallet createWallet(Wallet wallet) {
        if(wallet.getBalance() == null) wallet.setBalance(0.0);
        return repository.save(wallet);
    }

    @Override
    public List<Wallet> getAllWallets() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Wallet loadMoney(LoadRequest req) {
        Wallet wallet = repository.findById(req.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with ID: " + req.getId()));
        wallet.setBalance(wallet.getBalance() + req.getAmount());
        return repository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet withdrawMoney(WithdrawRequest req) {
        Wallet wallet = repository.findById(req.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with ID: " + req.getId()));
        if (wallet.getBalance() < req.getAmount()) {
            throw new InsufficientBalanceException("Insufficient funds. Current balance: " + wallet.getBalance());
        }
        wallet.setBalance(wallet.getBalance() - req.getAmount());
        return repository.save(wallet);
    }

    @Transactional
    public void transferMoney(TransferRequest req) {
        if (req.getFromMobile().equals(req.getToMobile())) {
            throw new IllegalArgumentException("Sender and Receiver mobile numbers must be different.");
        }

        Wallet from = repository.findByMobile(req.getFromMobile())
                .orElseThrow(() -> new RuntimeException("Sender wallet not found"));
        Wallet to = repository.findByMobile(req.getToMobile())
                .orElseThrow(() -> new RuntimeException("Receiver wallet not found"));

        if (from.getBalance() < req.getAmount()) {
            throw new RuntimeException("Insufficient funds in sender wallet.");
        }

        from.setBalance(from.getBalance() - req.getAmount());
        to.setBalance(to.getBalance() + req.getAmount());

        repository.save(from);
        repository.save(to);
    }

    @Override
    public void deleteWallet(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Wallet not found with id: " + id);
        }
        repository.deleteById(id); 
    }
}