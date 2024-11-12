package com.example.transactionservice.repository;

import com.example.transactionservice.entity.PaymentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentsRequestRepository extends JpaRepository<PaymentRequest,UUID> {
}
