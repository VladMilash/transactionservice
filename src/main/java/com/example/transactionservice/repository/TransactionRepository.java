package com.example.transactionservice.repository;

import com.example.transactionservice.entity.Transaction;
import com.example.transactionservice.entity.enums.State;
import com.example.transactionservice.entity.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT t FROM Transaction t WHERE " +
            "(:userUid IS NULL OR t.userUid = :userUid) AND " +
            "(:walletUid IS NULL OR t.wallet.uid = :walletUid) AND " +
            "(:type IS NULL OR t.type = :type) AND " +
            "(:state IS NULL OR t.state = :state) AND " +
            "(:dateFrom IS NULL OR t.createdAt >= :dateFrom) AND " +
            "(:dateTo IS NULL OR t.createdAt <= :dateTo)")
    Optional<List<Transaction>> getTransactionsByFilters(@Param("userUid") UUID userUid,
                                                        @Param("walletUid") UUID walletUid,
                                                        @Param("type") TransactionType type,
                                                        @Param("state") State state,
                                                        @Param("dateFrom") LocalDateTime dateFrom,
                                                        @Param("dateTo") LocalDateTime dateTo);
}

