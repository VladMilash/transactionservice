package com.example.transactionservice.rest;

import com.example.transactionservice.dto.WalletResponseDTO;
import com.example.transactionservice.dto.WalletTypeDTO;
import com.example.transactionservice.service.WalletService;
import com.example.transactionservice.service.WalletTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/wallets")
public class WalletsRestControllerV1 {
    private final WalletService walletService;
    private final WalletTypeService walletTypeService;

    @GetMapping("/user/{user_uid}")
    public List<WalletResponseDTO> getWalletsByUserUid(@PathVariable("user_uid") UUID userUid) {
        return walletService.findByUserUid(userUid);
    }

    @GetMapping("/user/{user_uid}/currency/{currency}")
    public WalletResponseDTO getWalletByUserUidAndCurrencyCode(@PathVariable("user_uid") UUID userUid,
                                                               @PathVariable("currency") String currency) {
        return walletService.findAllByUserUidAndCurrencyCode(userUid, currency.toUpperCase());
    }

    @PostMapping("/type")
    public WalletTypeDTO saveWalletType(@RequestBody WalletTypeDTO walletTypeDTO) {
        return walletTypeService.createWalletType(walletTypeDTO);
    }

    @GetMapping("/type")
    private List<WalletTypeDTO> getAllWalletsType() {
        return walletTypeService.getAllWalletsType();
    }
}
