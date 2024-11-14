package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.CreateWalletRequestDTO;
import com.example.transactionservice.entity.Wallet;
import com.example.transactionservice.entity.WalletType;
import com.example.transactionservice.entity.enums.WalletStatus;
import com.example.transactionservice.repository.WalletRepository;
import com.example.transactionservice.service.WalletService;
import com.example.transactionservice.service.WalletTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private WalletTypeService walletTypeService;

    @Override
    public Wallet createWallet(CreateWalletRequestDTO createWalletRequestDTO) {
        WalletType walletType = walletTypeService.getByUserType(createWalletRequestDTO.userType());
        Wallet wallet = createWalletEntity(createWalletRequestDTO, walletType);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet getById(UUID id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("WalletType with id " + id + " not found"));
    }

    @Override
    public Wallet update(Wallet wallet) {
        return null;
//                TODO:implements logic
//        Wallet forUpdate = getById(wallet.getUid());
//        Wallet foundedWallet = getById(wallet.getUid())

    }

    private Wallet createWalletEntity(CreateWalletRequestDTO createWalletRequestDTO,
                                      WalletType walletType) {
        return Wallet.builder()
                .name(createWalletRequestDTO.name())
                .status(WalletStatus.ACTIVE)
                .userUid(createWalletRequestDTO.user_uid())
                .walletType(walletType)
                .build();
    }
}
