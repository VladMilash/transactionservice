package com.example.transactionservice.service;

import com.example.transactionservice.dto.CreateWalletRequestDTO;
import com.example.transactionservice.entity.Wallet;

import java.util.UUID;

public interface WalletService {
    Wallet createWallet(CreateWalletRequestDTO createWalletRequestDTO);
    Wallet getById(UUID id);
    Wallet update(Wallet wallet);
}
