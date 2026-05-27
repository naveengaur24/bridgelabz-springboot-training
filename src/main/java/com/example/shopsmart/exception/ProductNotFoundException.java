package com.example.shopsmart.exception;

public class ProductNotFoundException extends ShopSmartException {
    public ProductNotFoundException(Long id) {
        super("Product not found with id: " + id, "PRODUCT_NOT_FOUND");
    }
}