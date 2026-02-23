package com.example.walletApp.service;

import com.example.walletApp.dto.*;
import com.example.walletApp.model.Wallet;
import java.util.List;

public interface WalletService {
    Wallet createWallet(Wallet wallet);
    List<Wallet> getAllWallets();
    Wallet loadMoney(LoadRequest request);
    Wallet withdrawMoney(WithdrawRequest request);
    void transferMoney(TransferRequest request);
    void deleteWallet(Long id);
}