package com.senla.bankApp.exception;

public class AuthorizationException extends RuntimeException{

    private final String massage = "Ошибка авторизации";

    public AuthorizationException(String message) {
        super(message);
    }
}
