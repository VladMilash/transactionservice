package com.example.transactionservice.repository;

import com.example.transactionservice.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    @Query(value = "SELECT * FROM wallets WHERE uid = :id",
            nativeQuery = true)
    Optional<Wallet> findById(UUID id);

    @Query("SELECT w FROM Wallet w LEFT JOIN FETCH w.walletType WHERE w.userUid = :userUid")
    Optional<List<Wallet>> findAllByUserUid(UUID userUid);

    @Query("SELECT w FROM Wallet w LEFT JOIN FETCH w.walletType WHERE w.userUid = :userUid AND w.walletType.currencyCode = :currencyCode")
    Optional<Wallet> findAllByUserUidAndCurrencyCode(UUID userUid, String currencyCode);
}
