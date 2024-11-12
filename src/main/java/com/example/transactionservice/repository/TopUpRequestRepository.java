package com.example.transactionservice.repository;

import com.example.transactionservice.entity.TopUpRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TopUpRequestRepository extends JpaRepository<TopUpRequest,UUID> {
}
