package com.example.transactionservice.exception;

public class NotFoundEntityException extends ApiException {
    public NotFoundEntityException(String message, String errorCode) {
        super(message, errorCode);
    }
}
