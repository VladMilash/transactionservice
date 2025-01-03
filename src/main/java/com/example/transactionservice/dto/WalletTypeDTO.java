package com.example.transactionservice.dto;

import com.example.transactionservice.entity.enums.UserType;
import com.example.transactionservice.entity.enums.WalletTypesStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record WalletTypeDTO(UUID uid, LocalDateTime createdAt, LocalDateTime modifiedAt, String name,
                            String currencyCode,
                            WalletTypesStatus status, LocalDateTime archivedAt, UserType userType,
                            String creator, String modifier) {
}
