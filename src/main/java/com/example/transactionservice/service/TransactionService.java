package com.example.transactionservice.service;

import com.example.transactionservice.dto.TransactionRequestSearchDTO;
import com.example.transactionservice.entity.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);

    List<Transaction> getTransactionsByFilters(TransactionRequestSearchDTO transactionRequestSearchDTO);
}
