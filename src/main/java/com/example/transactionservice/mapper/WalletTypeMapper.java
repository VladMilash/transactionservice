package com.example.transactionservice.mapper;

import com.example.transactionservice.dto.WalletTypeDTO;
import com.example.transactionservice.entity.WalletType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletTypeMapper {
    WalletTypeDTO map(WalletType walletType);

    @InheritInverseConfiguration
    WalletType map(WalletTypeDTO walletTypeDTO);
}
