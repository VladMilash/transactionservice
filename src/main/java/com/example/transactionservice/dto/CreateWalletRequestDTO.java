package com.example.transactionservice.dto;

import com.example.transactionservice.entity.enums.UserType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record CreateWalletRequestDTO(
        @NotNull
        String name,

        @NotNull
        UUID user_uid,

        @NotNull
        UserType userType) {
}
