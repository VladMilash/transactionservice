package com.example.transactionservice.repository;

import com.example.transactionservice.entity.WalletType;
import com.example.transactionservice.entity.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface WalletTypeRepository extends JpaRepository<WalletType, UUID> {
    Optional<WalletType> getWalletTypeByName(String name);

    Optional<WalletType> findByUserType(UserType userType);
}
