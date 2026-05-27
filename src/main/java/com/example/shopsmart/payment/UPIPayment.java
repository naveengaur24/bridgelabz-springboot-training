package com.example.shopsmart.payment;
public class UPIPayment implements PaymentMethod {

    private String upiId;
    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public double processPayment(double amount) {
        if(!upiId.contains("@")) {
            throw new RuntimeException("Invalid UPI ID");
        }
        return amount;
    }
}