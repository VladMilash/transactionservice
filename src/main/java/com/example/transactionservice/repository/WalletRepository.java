package com.example.transactionservice.repository;

import com.example.transactionservice.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    @Query(value = "SELECT wallet FROM wallets WHERE uid = :id",
            nativeQuery = true)
    Optional<Wallet> findById(UUID id);
}
