package com.example.transactionservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "top_up_requests")
@Data
public class TopUpRequest {

    @Id
    @GeneratedValue
    @Column(name = "uid")
    private UUID uid;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "UUID")
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "provider")
    private String provider;

    @OneToOne
    @JoinColumn(name = "payment_request_uid")
    private PaymentRequest paymentRequest;


}
