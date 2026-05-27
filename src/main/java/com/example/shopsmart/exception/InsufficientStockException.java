package com.example.shopsmart.exception;

public class InsufficientStockException extends ShopSmartException {
    public InsufficientStockException(String productName, int available) {
        super("Insufficient stock for: " + productName + ". Available: " + available, "INSUFFICIENT_STOCK");
    }
}