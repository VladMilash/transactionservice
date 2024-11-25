package com.example.transactionservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TransferRequestDTO(
        UUID userIdFrom,
        UUID userIdTo,
        Double amount,
        String comment,
        Long paymentMethodId,
        String currency) {
}
