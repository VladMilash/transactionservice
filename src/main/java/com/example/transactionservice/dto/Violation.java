package com.example.transactionservice.dto;

public record Violation(
        String fieldName,
        String message) {
}
