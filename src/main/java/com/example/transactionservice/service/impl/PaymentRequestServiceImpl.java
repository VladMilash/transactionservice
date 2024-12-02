package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.TopUpRequestDTO;
import com.example.transactionservice.dto.TransactionResponseDTO;
import com.example.transactionservice.entity.PaymentRequest;
import com.example.transactionservice.entity.TopUpRequest;
import com.example.transactionservice.entity.Transaction;
import com.example.transactionservice.entity.Wallet;
import com.example.transactionservice.entity.enums.State;
import com.example.transactionservice.entity.enums.TransactionType;
import com.example.transactionservice.mapper.TransactionMapper;
import com.example.transactionservice.repository.PaymentsRequestRepository;
import com.example.transactionservice.service.PaymentRequestService;
import com.example.transactionservice.service.TransactionService;
import com.example.transactionservice.service.WalletService;
import com.example.transactionservice.sharding.ShardContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentRequestServiceImpl implements PaymentRequestService {
    private final WalletService walletService;
    private final PaymentsRequestRepository paymentsRequestRepository;
    private final TopUpRequestServiceImpl topUpRequestService;
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @Override
    public TransactionResponseDTO startTopUpTransaction(TopUpRequestDTO topUpRequestDTO) {
        log.info("Started operation for topUp with user_uid: {}, wallet_uid: {}, amount: {}",
                topUpRequestDTO.userUid(), topUpRequestDTO.walletUid(), topUpRequestDTO.amount());

        ShardContext.determineAndSetShard(topUpRequestDTO.userUid());

        try {
            Wallet walletForTopUp = walletService.getById(topUpRequestDTO.walletUid());

            PaymentRequest paymentRequest = paymentsRequestRepository.save(PaymentRequest.builder()
                    .createdAt(LocalDateTime.now())
                    .modifiedAt(LocalDateTime.now())
                    .userUid(topUpRequestDTO.userUid())
                    .wallet(walletForTopUp)
                    .amount(topUpRequestDTO.amount())
                    .status(State.CREATED)
                    .build());

            TopUpRequest topUpRequest = topUpRequestService.saveTopUpRequest(TopUpRequest.builder()
                    .createdAt(LocalDateTime.now())
                    .paymentRequest(paymentRequest)
                    .provider("default")
                    .build());

            Transaction transaction = transactionService.saveTransaction(Transaction.builder()
                    .createdAt(LocalDateTime.now())
                    .userUid(topUpRequestDTO.userUid())
                    .wallet(walletForTopUp)
                    .walletName(walletForTopUp.getName())
                    .amount(topUpRequestDTO.amount())
                    .state(State.CREATED)
                    .paymentRequest(paymentRequest)
                    .type(TransactionType.TOP_UP)
                    .build());

            topUpRequestService.processingTopUpTransaction(paymentRequest, topUpRequest, transaction, walletForTopUp, topUpRequestDTO.amount());

            return transactionMapper.map(transaction);

        } finally {
            ShardContext.clear();
        }
    }

    @Override
    public PaymentRequest savePaymentRequest(PaymentRequest paymentRequest) {
        return paymentsRequestRepository.save(paymentRequest);
    }


    @Override
    public PaymentRequest updatePaymentRequestStatus(PaymentRequest paymentRequestForUpdate, State newState) {
        paymentRequestForUpdate.setStatus(newState);
        return savePaymentRequest(paymentRequestForUpdate);
    }
}
