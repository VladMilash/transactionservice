package com.example.transactionservice.dto;

import com.example.transactionservice.entity.enums.State;
import com.example.transactionservice.entity.enums.TransactionType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TransactionRequestSearchDTO(
        UUID userUid,
        UUID walletUid,
        TransactionType transactionType,
        State state,
        LocalDateTime dateFrom,
        LocalDateTime dateTo) {
}
