package com.example.transactionservice.service;

import com.example.transactionservice.entity.PaymentRequest;
import com.example.transactionservice.entity.TopUpRequest;
import com.example.transactionservice.entity.Transaction;
import com.example.transactionservice.entity.Wallet;
import com.example.transactionservice.entity.enums.State;

import java.math.BigDecimal;

public interface TopUpRequestService {
    TopUpRequest saveTopUpRequest(TopUpRequest topUpRequest);

    public void processingTopUpTransaction(PaymentRequest paymentRequest,
                                           TopUpRequest topUpRequest,
                                           Transaction transaction,
                                           Wallet wallet,
                                           BigDecimal amount);
}
