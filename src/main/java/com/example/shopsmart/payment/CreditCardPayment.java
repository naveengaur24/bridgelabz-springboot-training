package com.example.shopsmart.payment;
import lombok.*;
@Data
@AllArgsConstructor
public class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;

    @Override
    public double processPayment(double amount) {
        if (cardNumber.length() != 16)
            throw new IllegalArgumentException("Card ka no. must be 16 digits..");
        return amount * 1.02; // 2% fee
    }
}