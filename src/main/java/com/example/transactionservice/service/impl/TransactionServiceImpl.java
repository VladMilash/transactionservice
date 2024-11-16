package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.TransactionRequestSearchDTO;
import com.example.transactionservice.dto.TransactionResponseDTO;
import com.example.transactionservice.entity.Transaction;
import com.example.transactionservice.exception.ApiException;
import com.example.transactionservice.exception.NotFoundEntityException;
import com.example.transactionservice.mapper.TransactionMapper;
import com.example.transactionservice.repository.TransactionRepository;
import com.example.transactionservice.service.TransactionService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@lombok.extern.slf4j.Slf4j
@RequiredArgsConstructor
@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        log.info("Transaction with id: {} saved successfully", savedTransaction.getUid());

        return savedTransaction;
    }

    @Override
    public List<TransactionResponseDTO> getTransactionsByFilters(TransactionRequestSearchDTO transactionRequestSearchDTO) {
        List<TransactionResponseDTO> transactions = transactionRepository.getTransactionsByFilters(
                        transactionRequestSearchDTO.userUid(),
                        transactionRequestSearchDTO.walletUid(),
                        transactionRequestSearchDTO.type(),
                        transactionRequestSearchDTO.state(),
                        transactionRequestSearchDTO.dateFrom(),
                        transactionRequestSearchDTO.dateTo()
                ).map(transactions1 -> {
                    return transactions1;
                })
                .orElseThrow(() -> {
                    return new ApiException("ERR", "ERR");
                }).stream().map(transactionMapper::map).toList();
    }
}
