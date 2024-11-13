package com.example.transactionservice.entity;

import com.example.transactionservice.entity.enums.State;
import com.example.transactionservice.entity.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
public class Transactions {
    @Id
    @GeneratedValue
    @Column(name = "uid", columnDefinition = "UUID")
    private UUID uid;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @NotNull
    @Column(name = "user_uid")
    private UUID userUid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_uid")
    private Wallet wallet;

    @NotNull
    @Column(name = "wallet_name", length = 32)
    private String walletName;

    @NotNull
    @Column(name = "amount")
    private BigDecimal amount = BigDecimal.ZERO;

    @NotNull
    @Column(name = "type")
    private TransactionType type;

    @NotNull
    @Column(name = "state")
    private State state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_request_uid")
    private PaymentRequest paymentRequest;


}
