package com.github.aleksey7877.marketplace.productservice.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("product with id " + id + " was not found");
    }
}