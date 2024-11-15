package com.example.transactionservice.service.impl;

import com.example.transactionservice.repository.TransactionsRepository;
import com.example.transactionservice.service.TransactionService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TransactionsRepository transactionsRepository;

    @Override
    public TransactionService createTransaction(TransactionService transaction) {
        return null;
    }
}
