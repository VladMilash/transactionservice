package com.example.transactionservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TransferResponseDTO(
        UUID requestId,
        UUID userIdFrom,
        UUID userIdTo,
        UUID transactionIdFrom,
        UUID transactionIdTo,
        Double amount,
        String currency,
        String state,
        LocalDateTime createdAt) {
}
