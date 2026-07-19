package com.github.aleksey7877.marketplace.productservice.exception;

public class UserIsNotSellerException extends RuntimeException {

    public UserIsNotSellerException(long userId) {
        super("user with id " + userId + " is not a seller");
    }
}