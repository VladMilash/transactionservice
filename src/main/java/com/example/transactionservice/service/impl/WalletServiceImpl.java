package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.CreateWalletRequestDTO;
import com.example.transactionservice.entity.Wallet;
import com.example.transactionservice.entity.WalletType;
import com.example.transactionservice.entity.enums.WalletStatus;
import com.example.transactionservice.exception.NotFoundEntityException;
import com.example.transactionservice.repository.WalletRepository;
import com.example.transactionservice.service.WalletService;
import com.example.transactionservice.service.WalletTypeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.jaxb.core.v2.TODO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.UUID;

@Validated
@RequiredArgsConstructor
@Slf4j
@Service
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final WalletTypeService walletTypeService;

    @Override
    public Wallet createWallet(@Valid CreateWalletRequestDTO createWalletRequestDTO) {
        log.info("Creating wallet for user with UID: {}", createWalletRequestDTO.user_uid());

        WalletType walletType = walletTypeService.getByUserType(createWalletRequestDTO.userType());
        log.info("WalletType found: {}", walletType);

        Wallet wallet = createWalletEntity(createWalletRequestDTO, walletType);
        log.info("Wallet entity created: {}", wallet);

        Wallet savedWallet = walletRepository.save(wallet);
        log.info("Wallet successfully created with ID: {}", savedWallet.getUid());

        return savedWallet;
    }

    @Override
    public Wallet getById(@NotNull UUID id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Wallet with id {} not found", id);
                    return new NotFoundEntityException("Wallet with id " + id + " not found", "NOT_FOUND_ENTITY");
                });
    }

    //    TODO: подумать может стоит принимать DTO
    @Override
    public Wallet update(@Valid Wallet wallet) {
        log.info("Attempting to update wallet with ID: {}", wallet.getUid());

        Wallet foundedWallet = getById(wallet.getUid());
        log.info("Existing wallet found: {}", foundedWallet);

        BeanUtils.copyProperties(wallet, foundedWallet, "id", "createdAt", "walletType");
        foundedWallet.setModifiedAt(LocalDateTime.now());
        log.info("Wallet updated with new properties: {}", foundedWallet);

        WalletType foundedWalletType = walletTypeService.getByUserType(wallet.getWalletType().getUserType());
        log.info("WalletType found for userType: {}", wallet.getWalletType().getUserType());

        BeanUtils.copyProperties(foundedWalletType, foundedWallet.getWalletType(), "id", "createdAt");
        foundedWalletType.setModifiedAt(LocalDateTime.now());
        foundedWallet.setWalletType(foundedWalletType);
        log.info("WalletType in wallet updated with new properties: {}", foundedWalletType);

        Wallet updetedWallet = walletRepository.save(foundedWallet);
        log.info("Wallet with ID: {}, successfully updated ", updetedWallet.getUid());

        return updetedWallet;
    }

    private Wallet createWalletEntity(CreateWalletRequestDTO createWalletRequestDTO,
                                      WalletType walletType) {
        log.info("Creating Wallet entity with name: {} and user UID: {}",
                createWalletRequestDTO.name(), createWalletRequestDTO.user_uid());

        return Wallet.builder()
                .name(createWalletRequestDTO.name())
                .status(WalletStatus.ACTIVE)
                .userUid(createWalletRequestDTO.user_uid())
                .walletType(walletType)
                .build();
    }
}
