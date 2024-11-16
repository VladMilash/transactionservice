package com.example.transactionservice.dto;

import java.util.List;

public record ValidationErrorResponse(
        List<Violation> violations) {
}
