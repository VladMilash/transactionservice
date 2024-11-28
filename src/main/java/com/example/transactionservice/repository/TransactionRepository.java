package com.example.transactionservice.repository;

import com.example.transactionservice.entity.Transaction;
import com.example.transactionservice.entity.enums.State;
import com.example.transactionservice.entity.enums.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID>,
        PagingAndSortingRepository<Transaction, UUID> {

    @Query("SELECT t FROM Transaction t WHERE " +
            "(COALESCE(:userUid, t.userUid) = t.userUid) AND " +
            "(COALESCE(:walletUid, t.wallet.uid) = t.wallet.uid) AND " +
            "(COALESCE(:type, t.type) = t.type) AND " +
            "(COALESCE(:state, t.state) = t.state) AND " +
            "(COALESCE(:dateFrom, t.createdAt) <= t.createdAt) AND " +
            "(COALESCE(:dateTo, t.createdAt) >= t.createdAt)")
    Optional<List<Transaction>> getTransactionsByFilters(@Param("userUid") UUID userUid,
                                                         @Param("walletUid") UUID walletUid,
                                                         @Param("type") TransactionType type,
                                                         @Param("state") State state,
                                                         @Param("dateFrom") LocalDateTime dateFrom,
                                                         @Param("dateTo") LocalDateTime dateTo);

    @Query("SELECT t FROM Transaction t WHERE " +
            "(COALESCE(:userUid, t.userUid) = t.userUid) AND " +
            "(COALESCE(:walletUid, t.wallet.uid) = t.wallet.uid) AND " +
            "(COALESCE(:type, t.type) = t.type) AND " +
            "(COALESCE(:state, t.state) = t.state) AND " +
            "(COALESCE(:dateFrom, t.createdAt) <= t.createdAt) AND " +
            "(COALESCE(:dateTo, t.createdAt) >= t.createdAt)")
    Optional<Page<Transaction>> getTransactionsByFilters(@Param("userUid") UUID userUid,
                                                         @Param("walletUid") UUID walletUid,
                                                         @Param("type") TransactionType type,
                                                         @Param("state") State state,
                                                         @Param("dateFrom") LocalDateTime dateFrom,
                                                         @Param("dateTo") LocalDateTime dateTo,
                                                         Pageable pageable);


    Optional<Transaction> findTransactionByUid(UUID uid);
}

