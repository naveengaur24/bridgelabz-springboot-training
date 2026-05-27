package com.example.shopsmart.payment;
import lombok.*;
@Data
@AllArgsConstructor
public class UPIPayment implements PaymentMethod {
    private String upiId;

    @Override
    public double processPayment(double amount) {
        if (!upiId.contains("@"))
            throw new IllegalArgumentException("Invalid UPI ID");
        return amount; // no fee
    }
}