package com.example.transactionservice.service;

import com.example.transactionservice.dto.TopUpRequestDTO;
import com.example.transactionservice.dto.TransactionResponseDTO;
import com.example.transactionservice.entity.PaymentRequest;
import com.example.transactionservice.entity.TopUpRequest;
import com.example.transactionservice.entity.Transaction;
import com.example.transactionservice.entity.Wallet;
import com.example.transactionservice.entity.enums.State;

import java.math.BigDecimal;

public interface PaymentRequestService {
    TransactionResponseDTO startTopUpTransaction(TopUpRequestDTO topUpRequestDTO);

    PaymentRequest savePaymentRequest(PaymentRequest paymentRequest);

    PaymentRequest updatePaymentRequestStatus(PaymentRequest paymentRequestForUpdate,
                                              State newState);



}
