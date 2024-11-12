package com.example.transactionservice.repository;

import com.example.transactionservice.entity.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<WalletType, UUID> {
}
