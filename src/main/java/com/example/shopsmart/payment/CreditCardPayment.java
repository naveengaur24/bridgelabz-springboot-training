package com.example.shopsmart.payment;
public class CreditCardPayment implements PaymentMethod {

    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
    public CreditCardPayment(
            String cardNumber,
            String cardHolderName,
            String expiryDate,
            String cvv) {

        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public double processPayment(double amount) {
        if(cardNumber.length() != 16) {
            throw new RuntimeException("Card number must be 16 digits");
        }
        return amount + (amount * 0.02);
    }
}