package com.example.transactionservice.entity;

import com.example.transactionservice.entity.enums.UserType;
import com.example.transactionservice.entity.enums.WalletTypesStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "wallet_types")
@Data
public class WalletTypes {

    @Id
    @GeneratedValue
    @Column(name = "uid")
    private UUID uid;

    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @NotNull
    @Column(name = "name", length = 32)
    private String name;

    @NotNull
    @Column(name = "currency_code", length = 3)
    private String currencyCode;

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private WalletTypesStatus status;

    @Column(name = "archived_at")
    private LocalDateTime archivedAt;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "creator")
    private String creator;

    @Column(name = "modifier")
    private String modifier;


}
