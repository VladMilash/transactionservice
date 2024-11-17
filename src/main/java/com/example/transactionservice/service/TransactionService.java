package com.example.transactionservice.service;

import com.example.transactionservice.dto.TransactionRequestSearchDTO;
import com.example.transactionservice.dto.TransactionResponseDTO;
import com.example.transactionservice.entity.Transaction;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);

    List<TransactionResponseDTO> getTransactionsByFilters(TransactionRequestSearchDTO transactionRequestSearchDTO);

    Page<TransactionResponseDTO> getTransactionsByFilters(TransactionRequestSearchDTO transactionRequestSearchDTO, int page, int size);
}
