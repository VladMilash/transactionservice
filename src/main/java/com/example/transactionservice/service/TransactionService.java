package com.example.transactionservice.service;

import com.example.transactionservice.dto.TransactionRequestSearchDTO;
import com.example.transactionservice.dto.TransactionResponseDTO;
import com.example.transactionservice.dto.TransactionStatusResponseDTO;
import com.example.transactionservice.entity.Transaction;
import com.example.transactionservice.entity.enums.State;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    Transaction saveTransaction(@Valid Transaction transaction);

    List<TransactionResponseDTO> getTransactionsByFilters(@NotNull TransactionRequestSearchDTO transactionRequestSearchDTO);

    Page<TransactionResponseDTO> getTransactionsByFilters(@NotNull TransactionRequestSearchDTO transactionRequestSearchDTO, @Min(0) int page, @Min(1) @Max(100) int size);

    TransactionStatusResponseDTO getTransactionStatus(@NotNull UUID userUid, @NotNull UUID uid);

    void updateTransactionStatus(@NotNull Transaction transactionForUpdate, @NotNull State newStatus);

}
