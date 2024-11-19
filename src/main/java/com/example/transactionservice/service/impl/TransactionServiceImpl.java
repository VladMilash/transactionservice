package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.TransactionRequestSearchDTO;
import com.example.transactionservice.dto.TransactionResponseDTO;
import com.example.transactionservice.dto.TransactionStatusResponseDTO;
import com.example.transactionservice.entity.Transaction;
import com.example.transactionservice.exception.MethodArgumentNotValidCustomException;
import com.example.transactionservice.exception.NotFoundEntityException;
import com.example.transactionservice.mapper.TransactionMapper;
import com.example.transactionservice.repository.TransactionRepository;
import com.example.transactionservice.service.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Validated
@Slf4j
@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    //    TODO: подумать может стоит принимать DTO
    @Override
    public Transaction createTransaction(@Valid Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        log.info("Transaction with id: {} saved successfully", savedTransaction.getUid());

        return savedTransaction;
    }

    @Override
    public List<TransactionResponseDTO> getTransactionsByFilters(TransactionRequestSearchDTO transactionRequestSearchDTO) {
        log.info("Fetching transactions with filters: {}", transactionRequestSearchDTO);

        validateDateRange(transactionRequestSearchDTO.dateFrom(), transactionRequestSearchDTO.dateTo());

        return transactionRepository.getTransactionsByFilters(
                        transactionRequestSearchDTO.userUid(),
                        transactionRequestSearchDTO.walletUid(),
                        transactionRequestSearchDTO.type(),
                        transactionRequestSearchDTO.state(),
                        transactionRequestSearchDTO.dateFrom(),
                        transactionRequestSearchDTO.dateTo()
                ).map(transactions -> {
                    log.info("Successfully fetched {} transactions", transactions.size());
                    return transactions.stream().map(transactionMapper::map).toList();
                })
                .orElseThrow(() -> {
                    log.error("Transactions not found for the given filters: {}", transactionRequestSearchDTO);
                    return new NotFoundEntityException("Transactions not found for the given filters", "TRANSACTIONS_NOT_FOUND");
                });
    }

    @Override
    public Page<TransactionResponseDTO> getTransactionsByFilters(TransactionRequestSearchDTO transactionRequestSearchDTO, @Min(0) int page, @Min(1) @Max(100) int size) {
        log.info("Fetching transactions with filters: {}, page: {}, size: {}", transactionRequestSearchDTO, page, size);

        validateDateRange(transactionRequestSearchDTO.dateFrom(), transactionRequestSearchDTO.dateTo());

        Pageable pageable = PageRequest.of(page, size);

        Page<Transaction> transactionsPage = transactionRepository.getTransactionsByFilters(
                transactionRequestSearchDTO.userUid(),
                transactionRequestSearchDTO.walletUid(),
                transactionRequestSearchDTO.type(),
                transactionRequestSearchDTO.state(),
                transactionRequestSearchDTO.dateFrom(),
                transactionRequestSearchDTO.dateTo(),
                pageable).orElseThrow(() -> {
            log.error("Transactions not found for the given filters: {}, page: {}, size: {}", transactionRequestSearchDTO, page, size);
            return new NotFoundEntityException("Transactions not found for the given filters", "TRANSACTIONS_NOT_FOUND");
        });
        return transactionsPage.map(transactionMapper::map);
    }

    private void validateDateRange(LocalDateTime dateFrom, LocalDateTime dateTo) {
        if (dateFrom != null && dateTo != null) {
            if (dateFrom.isAfter(dateTo)) {
                log.error("Invalid date range: dateFrom: {} cannot be after dateTo: {}", dateFrom, dateTo);
                throw new MethodArgumentNotValidCustomException("Invalid date range: dateFrom cannot be after dateTo", "INVALID_DATE_RANGE");
            }
        } else if (dateFrom != null || dateTo != null) {
            log.error("Both dateFrom: {} and dateTo: {} must be provided", dateFrom, dateTo);
            throw new MethodArgumentNotValidCustomException("Both dateFrom and dateTo must be provided", "INVALID_DATE_RANGE");
        }
    }

    @Override
    public TransactionStatusResponseDTO getTransactionStatus(UUID uid) {
        return transactionRepository.findTransactionByUid(uid)
                .map(transaction -> {
                    log.info("Transactions with uid: {} successfully founded", uid);
                    return new TransactionStatusResponseDTO(
                            uid,
                            transaction.getState(),
                            transaction.getCreatedAt());
                })
                .orElseThrow(() -> {
                    log.error("Transactions with uid: {} not found", uid);
                    return new NotFoundEntityException("Transactions with uid: " + uid + " not found", "TRANSACTION_NOT_FOUND");
                });
    }
}
