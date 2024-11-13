package com.example.transactionservice.service.impl;

import com.example.transactionservice.entity.WalletType;
import com.example.transactionservice.entity.enums.UserType;
import com.example.transactionservice.repository.WalletTypeRepository;
import com.example.transactionservice.service.WalletTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class WalletTypeServiceImpl implements WalletTypeService {
    private final WalletTypeRepository walletTypeRepository;

    @Override
    public WalletType getWalletTypeByName(String name) {
        return walletTypeRepository.getWalletTypeByName(name)
                .orElseThrow(() -> new RuntimeException("WalletType with name " + name + " not found"));
    }

    @Override
    public WalletType getByUserType(UserType userType) {
        return walletTypeRepository.findByUserType(userType)
                .orElseThrow(() -> new RuntimeException("WalletType with userType " + userType + " not found"));

    }
}
