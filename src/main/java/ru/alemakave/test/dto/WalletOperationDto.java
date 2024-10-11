package ru.alemakave.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.alemakave.test.model.WalletOperationType;

import java.util.Objects;
import java.util.UUID;

public class WalletOperationDto {
    @JsonProperty("valletId")
    private UUID walletId;
    private WalletOperationType operationType;
    private int amount;

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    public WalletOperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(WalletOperationType operationType) {
        this.operationType = operationType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "WalletOperationDto{" +
                "walletId=" + walletId +
                ", operationType=" + operationType +
                ", amount=" + amount +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WalletOperationDto that)) return false;

        return amount == that.amount && Objects.equals(walletId, that.walletId) && operationType == that.operationType;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(walletId);
        result = 31 * result + Objects.hashCode(operationType);
        result = 31 * result + amount;
        return result;
    }
}
