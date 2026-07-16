package com.github.aleksey7877.marketplace.userservice.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(long id) {
        super("user with id " + id + " was not found");
    }
}