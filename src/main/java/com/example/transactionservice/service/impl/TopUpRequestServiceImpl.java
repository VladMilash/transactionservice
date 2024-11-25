package com.example.transactionservice.service.impl;

import com.example.transactionservice.entity.PaymentRequest;
import com.example.transactionservice.entity.TopUpRequest;
import com.example.transactionservice.entity.Transaction;
import com.example.transactionservice.entity.Wallet;
import com.example.transactionservice.entity.enums.State;
import com.example.transactionservice.repository.TopUpRequestRepository;
import com.example.transactionservice.service.PaymentRequestService;
import com.example.transactionservice.service.TopUpRequestService;
import com.example.transactionservice.service.TransactionService;
import com.example.transactionservice.service.WalletService;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class TopUpRequestServiceImpl implements TopUpRequestService {
    private final TopUpRequestRepository topUpRequestRepository;
    private final TransactionService transactionService;
    private final WalletService walletService;
    private final PaymentRequestService paymentRequestService;

    public TopUpRequestServiceImpl(TopUpRequestRepository topUpRequestRepository, TransactionService transactionService,
                                   WalletService walletService, @Lazy PaymentRequestService paymentRequestService) {
        this.topUpRequestRepository = topUpRequestRepository;
        this.transactionService = transactionService;
        this.walletService = walletService;
        this.paymentRequestService = paymentRequestService;
    }

    @Override
    public TopUpRequest saveTopUpRequest(TopUpRequest topUpRequest) {
        return topUpRequestRepository.save(topUpRequest);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Async
    @Override
    public void processingTopUpTransaction(PaymentRequest paymentRequest, TopUpRequest topUpRequest,
                                           Transaction transaction, Wallet wallet, BigDecimal amount) {
        transactionService.updateTransactionStatus(transaction, State.PROCESSING);

        walletService.changeBalance(transaction);

        transactionService.updateTransactionStatus(transaction, State.COMPLETED);

        paymentRequestService.updatePaymentRequestStatus(paymentRequest, State.COMPLETED);

    }
}
