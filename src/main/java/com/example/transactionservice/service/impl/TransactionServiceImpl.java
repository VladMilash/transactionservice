package com.example.transactionservice.service.impl;

import com.example.transactionservice.dto.TransactionRequestSearchDTO;
import com.example.transactionservice.dto.TransactionResponseDTO;
import com.example.transactionservice.entity.Transaction;
import com.example.transactionservice.exception.MethodArgumentNotValidCustomException;
import com.example.transactionservice.exception.NotFoundEntityException;
import com.example.transactionservice.mapper.TransactionMapper;
import com.example.transactionservice.repository.TransactionRepository;
import com.example.transactionservice.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

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

    //TODO: сделать рефакторинг велидации дат и также подумать насчет кейса, когда одна дата null, а другая - нет
    @Override
    public List<TransactionResponseDTO> getTransactionsByFilters(TransactionRequestSearchDTO transactionRequestSearchDTO) {
        log.info("Fetching transactions with filters: {}", transactionRequestSearchDTO);

        if ((transactionRequestSearchDTO.dateFrom() != null) && (transactionRequestSearchDTO.dateTo() != null)) {
            if (!validateLocalDateFromFilterSearch(transactionRequestSearchDTO.dateFrom(), transactionRequestSearchDTO.dateTo())) {
                throw new MethodArgumentNotValidCustomException("Invalid date range: dateFrom cannot be after dateTo", "INVALID_DATE_RAGE");
            }
        }
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
                    return new NotFoundEntityException("Transactions not found for the given filters", "TRANSACTIONS_NOT_FOUND");
                });
    }

    private boolean validateLocalDateFromFilterSearch(LocalDateTime dateFrom, LocalDateTime dateTo) {
        return (dateFrom.isBefore(dateTo));
    }
}
