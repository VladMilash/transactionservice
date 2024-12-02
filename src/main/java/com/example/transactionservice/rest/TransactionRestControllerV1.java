package com.example.transactionservice.rest;

import com.example.transactionservice.dto.TopUpRequestDTO;
import com.example.transactionservice.dto.TransactionRequestSearchDTO;
import com.example.transactionservice.dto.TransactionResponseDTO;
import com.example.transactionservice.dto.TransactionStatusResponseDTO;
import com.example.transactionservice.entity.enums.State;
import com.example.transactionservice.entity.enums.TransactionType;
import com.example.transactionservice.service.PaymentRequestService;
import com.example.transactionservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/transactions")
public class TransactionRestControllerV1 {
    private final TransactionService transactionService;
    private final PaymentRequestService paymentRequestService;

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

    @GetMapping("/{uid}/status")
    public TransactionStatusResponseDTO getTransactionStatus(@RequestHeader("user_uid") UUID userUid, @PathVariable("uid") UUID uid) {
        log.info("Started getting transaction status for user with uid: {} for transaction uid: {}", userUid, uid);
        return transactionService.getTransactionStatus(userUid, uid);
    }

    @PostMapping("/topUp")
    public TransactionResponseDTO topUp(@RequestBody TopUpRequestDTO topUpRequestDTO) {
        return paymentRequestService.startTopUpTransaction(topUpRequestDTO);
    }
}

