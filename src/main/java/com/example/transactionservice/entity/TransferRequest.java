package com.example.transactionservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "transfer_requests")
@Data
public class TransferRequest{

    @Id
    @GeneratedValue
    @Column(name = "uid", columnDefinition = "UUID")
    private UUID uid;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "system_rate")
    private String provider;

    @OneToOne
    @JoinColumn(name = "payment_request_from")
    private PaymentRequest paymentRequestFrom;

    @OneToOne
    @JoinColumn(name = "payment_request_to")
    private PaymentRequest paymentRequestTo;

}
