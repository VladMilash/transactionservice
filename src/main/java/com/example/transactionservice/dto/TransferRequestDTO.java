package com.example.transactionservice.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TransferRequestDTO(
        UUID userIdFrom,
        UUID userIdTo,
        Double amount,
        String comment,
        Long paymentMethodId,
        String currency) {
}
