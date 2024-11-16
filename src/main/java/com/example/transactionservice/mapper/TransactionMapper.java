package com.example.transactionservice.mapper;

import com.example.transactionservice.dto.TransactionResponseDTO;
import com.example.transactionservice.entity.Transaction;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionResponseDTO map(Transaction transaction);

    @InheritInverseConfiguration
    Transaction map(TransactionResponseDTO transactionResponseDTO);
}
