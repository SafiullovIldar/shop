package com.shop.demo.dao;

import com.shop.demo.entity.CreditCard;
import org.springframework.stereotype.Component;

@Component
public interface CreditCardDao {

    void createCreditCard(CreditCard creditCard);
    CreditCard getCreditCard(String creditCardId);
    CreditCard getCreditCardByCustomerId(String customerId);
}
