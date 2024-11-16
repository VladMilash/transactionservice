package com.example.transactionservice.dto;

public record ErrorMessage(
        String message,
        String errorCode
) {
}
