package com.example.transactionservice.service;

import com.example.transactionservice.dto.CreateWalletRequestDTO;
import com.example.transactionservice.dto.WalletResponseDTO;
import com.example.transactionservice.entity.Transaction;
import com.example.transactionservice.entity.Wallet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WalletService {
    Wallet createWallet(CreateWalletRequestDTO createWalletRequestDTO);

    Wallet getById(UUID id);

    Wallet update(Wallet wallet);

    List<WalletResponseDTO> findByUserUid(UUID userUid);

    WalletResponseDTO findAllByUserUidAndCurrencyCode(UUID userUid, String currencyCode);

    Wallet changeBalance(Transaction transaction);


}
