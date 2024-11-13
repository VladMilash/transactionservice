package com.example.transactionservice.service;

import com.example.transactionservice.entity.WalletType;
import com.example.transactionservice.entity.enums.UserType;

public interface WalletTypeService {
    WalletType getWalletTypeByName(String name);
    WalletType getByUserType(UserType userType);
}
