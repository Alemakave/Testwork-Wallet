package ru.alemakave.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alemakave.test.exception.WalletNotFoundException;
import ru.alemakave.test.exception.WalletOperationException;
import ru.alemakave.test.model.Wallet;
import ru.alemakave.test.repository.WalletRepository;
import ru.alemakave.test.service.WalletService;

import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public int getBalance(UUID uuid) {
        return walletRepository.findById(uuid).orElseThrow(WalletNotFoundException::new).getBalance();
    }

    @Override
    public void deposit(UUID uuid, int amount) {
        if (amount <= 0) {
            throw new WalletOperationException("Сумма для пополнения должна быть положительной");
        }

        Wallet wallet = walletRepository.findById(uuid).orElseThrow(WalletNotFoundException::new);
        wallet.setBalance(wallet.getBalance() + amount);
    }

    @Override
    public void withdraw(UUID uuid, int amount) {
        if (amount <= 0) {
            throw new WalletOperationException("Сумма для вывода должна быть положительной");
        }

        Wallet wallet = walletRepository.findById(uuid).orElseThrow(WalletNotFoundException::new);

        if (amount > wallet.getBalance()) {
            throw new WalletOperationException("Недостаточно средств");
        }

        wallet.setBalance(wallet.getBalance() - amount);
    }
}
