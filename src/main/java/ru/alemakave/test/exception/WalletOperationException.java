package ru.alemakave.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WalletOperationException extends RuntimeException {
    public WalletOperationException(String message) {
        super(message);
    }
}
