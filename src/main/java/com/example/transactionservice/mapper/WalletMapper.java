package com.example.transactionservice.mapper;

import com.example.transactionservice.dto.WalletResponseDTO;
import com.example.transactionservice.entity.Wallet;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletResponseDTO map(Wallet wallet);

    @InheritInverseConfiguration
    Wallet map(WalletResponseDTO walletResponseDTO);
}
