package com.github.aleksey7877.marketplace.productservice.exception;

public class SellerNotFoundException extends RuntimeException {

    public SellerNotFoundException(long sellerId) {
        super("seller with id " + sellerId + " was not found");
    }
}