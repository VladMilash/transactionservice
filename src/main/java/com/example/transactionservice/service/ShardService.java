package com.example.transactionservice.service;

import com.example.transactionservice.dto.TransferRequestDTO;
import com.example.transactionservice.dto.TransferResponseDTO;

public interface ShardService {
    TransferResponseDTO transfer(TransferRequestDTO requestDTO);

}
