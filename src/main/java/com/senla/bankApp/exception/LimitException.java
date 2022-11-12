package com.senla.bankApp.exception;

public class LimitException extends RuntimeException {

    public LimitException(String message) {
        super(message);
    }
}
