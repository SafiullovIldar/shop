package com.shop.demo.service;

import com.shop.demo.dto.CreditCardDto;
import org.springframework.stereotype.Service;

@Service
public interface CreditCardService {

    void createCreditCard(CreditCardDto dto);
    CreditCardDto getCreditCard(String creditCardId);
    CreditCardDto getCreditCardByCustomerId(String customerId);

}
