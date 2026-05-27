package com.example.shopsmart.payment;
public class CashOnDelivery implements PaymentMethod {
    @Override
    public double processPayment(double amount) {
        if(amount > 50000) {
            throw new RuntimeException("COD available only below 50000");
        }
        return amount + (amount * 0.05);
    }
}