package com.example.transactionservice.service;

import com.example.transactionservice.dto.CreateWalletRequestDTO;
import com.example.transactionservice.entity.Wallet;

public interface WalletService {
    Wallet createWallet(CreateWalletRequestDTO createWalletRequestDTO);
}
