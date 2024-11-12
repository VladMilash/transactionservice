package com.example.transactionservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "withdrawal_requests")
@Data
public class WithdrawalRequest {

    @Id
    @GeneratedValue
    @Column(name = "uid", columnDefinition = "UUID")
    private UUID uid;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "payment_request_uid")
    private PaymentRequest paymentRequest;
}
