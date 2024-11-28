package com.example.transactionservice.entity;

import com.example.transactionservice.entity.enums.WalletStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder(toBuilder = true)
@Entity
@Table(name = "wallets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

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
    @Column(name = "name", length = 32)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_type_uid")
    private WalletType walletType;

    @NotNull
    @Column(name = "user_uid")
    private UUID userUid;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status")
    private WalletStatus status;

    @NotNull
    @Column(name = "balance")
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(name = "archived_at")
    private LocalDateTime archivedAt;


}
