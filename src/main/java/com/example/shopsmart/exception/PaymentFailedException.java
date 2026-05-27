package com.example.shopsmart.exception;

public class PaymentFailedException extends ShopSmartException {
    public PaymentFailedException(String reason) {
        super("Payment failed: " + reason, "PAYMENT_FAILED");
    }
}