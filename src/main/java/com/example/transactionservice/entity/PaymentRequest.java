package com.example.transactionservice.entity;

import com.example.transactionservice.entity.enums.PaymentRequestStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_requests")
@Data
public class PaymentRequest {
    @Id
    @GeneratedValue
    @Column(name = "uid")
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
    @Column(name = "amount")
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(name = "status")
    private PaymentRequestStatus status;

    @Column(name = "comment")
    private String comment;

    @Column(name = "payment_method_id")
    private BigInteger paymentMethodId;
}
