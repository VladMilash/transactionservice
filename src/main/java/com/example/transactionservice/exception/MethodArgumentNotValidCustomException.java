package com.example.transactionservice.exception;

public class MethodArgumentNotValidCustomException extends ApiException{
    public MethodArgumentNotValidCustomException(String message, String errorCode) {
        super(message, errorCode);
    }
}
