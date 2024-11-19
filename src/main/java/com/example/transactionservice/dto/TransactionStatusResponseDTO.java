package com.example.transactionservice.dto;

import com.example.transactionservice.entity.enums.State;

import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionStatusResponseDTO(
        UUID transactionUid,
        State state,
        LocalDateTime updatedAt) {
}
