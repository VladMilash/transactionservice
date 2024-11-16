package com.example.transactionservice.service.impl;

import com.example.transactionservice.entity.WalletType;
import com.example.transactionservice.entity.enums.UserType;
import com.example.transactionservice.exception.NotFoundEntityException;
import com.example.transactionservice.repository.WalletTypeRepository;
import com.example.transactionservice.service.WalletTypeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@RequiredArgsConstructor
@Slf4j
@Service
public class WalletTypeServiceImpl implements WalletTypeService {
    private final WalletTypeRepository walletTypeRepository;

    @Override
    public WalletType getWalletTypeByName(@NotNull String name) {
        return walletTypeRepository.getWalletTypeByName(name)
                .map(walletType -> {
                    log.info("WalletType found with name: {}", name);
                    return walletType;
                })
                .orElseThrow(() -> {
                    log.error("WalletType with name {} not found", name);
                    return new NotFoundEntityException("WalletType with name " + name + " not found", "NOT_FOUND_ENTITY");

                });
    }

    //    TODO: подумать насчет того чтобы принимать DTO
    @Override
    public WalletType getByUserType(@Valid UserType userType) {
        return walletTypeRepository.findByUserType(userType)
                .map(walletType -> {
                    log.info("WalletType found with userType: {}", userType);
                    return walletType;
                })
                .orElseThrow(() -> {
                    log.error("WalletType with userType {} not found", userType);
                    return new NotFoundEntityException("WalletType with userType " + userType + " not found", "NOT_FOUND_ENTITY");
                });

    }
}
