package ru.alemakave.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alemakave.test.dto.WalletOperationDto;
import ru.alemakave.test.service.WalletService;

import java.util.UUID;

@RestController()
@RequestMapping("/api/v1")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @GetMapping("/wallets/{WALLET_UUID}")
    public ResponseEntity<Integer> getBalance(@PathVariable("WALLET_UUID") UUID walletUuid) {
        return ResponseEntity.ok(walletService.getBalance(walletUuid));
    }

    @PostMapping(value = "/wallet", consumes = "application/json")
    public ResponseEntity<Void> postOperationBalance(@RequestBody WalletOperationDto walletOperationDto) {
        switch (walletOperationDto.getOperationType()) {
            case DEPOSIT:
                walletService.deposit(walletOperationDto.getWalletId(), walletOperationDto.getAmount());
                break;
            case WITHDRAW:
                walletService.withdraw(walletOperationDto.getWalletId(), walletOperationDto.getAmount());
                break;
        }

        return ResponseEntity.ok().build();
    }
}
