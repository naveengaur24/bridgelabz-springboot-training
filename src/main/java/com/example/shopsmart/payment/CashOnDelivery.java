package com.example.shopsmart.payment;
public class CashOnDelivery implements PaymentMethod {

    @Override
    public double processPayment(double amount) {
        if (amount > 50000)
            throw new IllegalArgumentException("COD not available above ₹50,000");
        return amount * 1.05; // 5% COD fee
    }
}