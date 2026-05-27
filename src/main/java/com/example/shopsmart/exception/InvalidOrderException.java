package com.example.shopsmart.exception;
public class InvalidOrderException extends ShopSmartException {
    public InvalidOrderException(String reason) {
        super("Invalid order: " + reason, "INVALID_ORDER");
    }
}