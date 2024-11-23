package com.example.transactionservice.service;

import com.example.transactionservice.dto.TransactionRequestSearchDTO;
import com.example.transactionservice.dto.TransactionResponseDTO;
import com.example.transactionservice.dto.TransactionStatusResponseDTO;
import com.example.transactionservice.entity.Transaction;
import com.example.transactionservice.entity.enums.State;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);

    List<TransactionResponseDTO> getTransactionsByFilters(TransactionRequestSearchDTO transactionRequestSearchDTO);

    Page<TransactionResponseDTO> getTransactionsByFilters(TransactionRequestSearchDTO transactionRequestSearchDTO, int page, int size);

    TransactionStatusResponseDTO getTransactionStatus(UUID userUid, UUID uid);

    void updateTransactionStatus(Transaction transactionForUpdate, State newStatus);

}
