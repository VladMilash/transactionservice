package com.example.transactionservice.service.impl;

import com.example.transactionservice.entity.PaymentRequest;
import com.example.transactionservice.entity.TopUpRequest;
import com.example.transactionservice.entity.Transaction;
import com.example.transactionservice.entity.Wallet;
import com.example.transactionservice.repository.TopUpRequestRepository;
import com.example.transactionservice.service.TopUpRequestService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class TopUpRequestServiceImpl implements TopUpRequestService {
    private final TopUpRequestRepository topUpRequestRepository;

    @Override
    public TopUpRequest saveTopUpRequest(TopUpRequest topUpRequest) {
        return topUpRequestRepository.save(topUpRequest);
    }

    @Transactional
    @Async
    @Override
    public void processingTopUpTransaction(PaymentRequest paymentRequest, TopUpRequest topUpRequest, Transaction transaction, Wallet wallet, BigDecimal amount) {

    }
}
