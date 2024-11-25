package com.example.transactionservice.service;

import com.example.transactionservice.dto.CreateWalletRequestDTO;
import com.example.transactionservice.dto.WalletResponseDTO;
import com.example.transactionservice.entity.Transaction;
import com.example.transactionservice.entity.Wallet;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WalletService {
    Wallet createWallet(CreateWalletRequestDTO createWalletRequestDTO);

    Wallet getById(@NotNull UUID userUid);

    Wallet update(Wallet wallet);

    List<WalletResponseDTO> findByUserUid(UUID userUid);

    WalletResponseDTO findAllByUserUidAndCurrencyCode(@NotNull UUID userUid,
                                                      @NotNull @Size(min = 3, max = 3, message = "{validation.name.size}") String currencyCode);

    Wallet changeBalance(Transaction transaction);


}
