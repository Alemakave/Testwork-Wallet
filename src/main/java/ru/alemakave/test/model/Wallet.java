package ru.alemakave.test.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity(name = "wallets")
public class Wallet {
    @Id
    private UUID id;
    private int balance;

    protected Wallet() {}

    public Wallet(UUID id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int amount) {
        this.balance = amount;
    }
}
