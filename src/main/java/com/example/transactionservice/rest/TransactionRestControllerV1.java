package com.example.transactionservice.rest;

import com.example.transactionservice.dto.TransactionRequestSearchDTO;
import com.example.transactionservice.dto.TransactionResponseDTO;
import com.example.transactionservice.entity.enums.State;
import com.example.transactionservice.entity.enums.TransactionType;
import com.example.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/transactions")
public class TransactionRestControllerV1 {
    private final TransactionService transactionService;

    @GetMapping
    public Page<TransactionResponseDTO> getTransactionsByFilter(
            @RequestParam(value = "user_uid", required = false) UUID userUid,
            @RequestParam(value = "wallet_uid", required = false) UUID walletUid,
            @RequestParam(value = "type", required = false) TransactionType type,
            @RequestParam(value = "state", required = false) State state,
            @RequestParam(value = "date_from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
            @RequestParam(value = "date_to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        TransactionRequestSearchDTO transactionRequestSearchDTO = new TransactionRequestSearchDTO(
                userUid, walletUid, type, state, dateFrom, dateTo);
        return transactionService.getTransactionsByFilters(transactionRequestSearchDTO, page, size);
    }
}

