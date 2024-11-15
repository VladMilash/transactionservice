package com.example.transactionservice.service;

import com.example.transactionservice.entity.Transaction;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);
}
