package com.github.aleksey7877.marketplace.userservice.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("username or email is already in use");
    }
}