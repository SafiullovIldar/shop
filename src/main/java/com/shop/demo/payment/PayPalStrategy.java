package com.shop.demo.payment;

public class PayPalStrategy implements PaymentStrategy {

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using PayPal");
    }
}
