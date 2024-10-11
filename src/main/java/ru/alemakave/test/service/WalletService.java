package ru.alemakave.test.service;

import java.util.UUID;

public interface WalletService {
    int getBalance(UUID uuid);
    void deposit(UUID uuid, int amount);
    void withdraw(UUID uuid, int amount);
}
