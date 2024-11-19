package com.example.transactionservice.dto;

import com.example.transactionservice.entity.WalletType;
import com.example.transactionservice.entity.enums.WalletStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record WalletResponseDTO(
        UUID uid,
        LocalDateTime createdAt,
        String name,
        WalletType walletType,
        UUID userUid,
        WalletStatus status,
        BigDecimal balance,
        LocalDateTime archivedAt) {
}
