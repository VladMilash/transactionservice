package com.example.transactionservice.repository;

import com.example.transactionservice.entity.WithdrawalRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WithdrawalRequestRepository extends JpaRepository<WithdrawalRequest,UUID> {
}