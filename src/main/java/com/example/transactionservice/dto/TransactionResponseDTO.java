package com.example.transactionservice.dto;

import com.example.transactionservice.entity.PaymentRequest;
import com.example.transactionservice.entity.Wallet;
import com.example.transactionservice.entity.enums.State;
import com.example.transactionservice.entity.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TransactionResponseDTO(
        UUID id,
        LocalDateTime createdAt,
        UUID userUid,
        Wallet wallet,
        String walletName,
        BigDecimal amount,
        TransactionType type,
        State state) {
}
