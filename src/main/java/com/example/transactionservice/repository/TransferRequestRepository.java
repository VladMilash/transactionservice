package com.example.transactionservice.repository;

import com.example.transactionservice.entity.TransferRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferRequestRepository extends JpaRepository<TransferRequest,UUID> {
}
