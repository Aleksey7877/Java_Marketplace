package com.github.aleksey7877.marketplace.productservice.exception;

public class UserServiceUnavailableException
        extends RuntimeException {

    public UserServiceUnavailableException(
            String message,
            Throwable cause
    ) {
        super(message, cause);
    }
}