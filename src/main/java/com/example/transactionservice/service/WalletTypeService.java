package com.example.transactionservice.service;

import com.example.transactionservice.dto.WalletTypeDTO;
import com.example.transactionservice.entity.WalletType;
import com.example.transactionservice.entity.enums.UserType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface WalletTypeService {
    WalletType getWalletTypeByName(@NotNull String name);
    WalletType getByUserType(@Valid UserType userType);
    WalletTypeDTO createWalletType(WalletTypeDTO walletTypeDTO);
    List<WalletTypeDTO> getAllWalletsType();
}
