package com.shop.demo.payment;

public class CreditCardStrategy implements PaymentStrategy {

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using credit card");
    }
}
