package com.example.transactionservice.service;

import com.example.transactionservice.entity.WalletType;
import com.example.transactionservice.entity.enums.UserType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface WalletTypeService {
    WalletType getWalletTypeByName(@NotNull String name);
    WalletType getByUserType(@Valid UserType userType);
}
