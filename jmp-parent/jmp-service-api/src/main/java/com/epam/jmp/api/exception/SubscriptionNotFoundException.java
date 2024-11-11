package com.epam.jmp.api.exception;

public class SubscriptionNotFoundException extends RuntimeException{

    public SubscriptionNotFoundException(String message) {
            super(message);
    }
}
