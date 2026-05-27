package com.example.shopsmart.payment;

public class PaymentTest {

    public static void main(String[] args) {
        PaymentMethod payment;
        payment = new CreditCardPayment(
                "1234567890123456",
                "Naveen",
                "12/30",
                "123"
        );
        System.out.println(payment.processPayment(1000));

        payment = new UPIPayment("naveen@upi");

        System.out.println(payment.processPayment(1000));
        payment = new CashOnDelivery();
        System.out.println(payment.processPayment(1000));
    }
}