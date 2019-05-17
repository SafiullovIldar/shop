package com.shop.demo.converter.dtotoentity;

import com.shop.demo.dto.CreditCardDto;
import com.shop.demo.entity.CreditCard;
import org.springframework.core.convert.converter.Converter;

public class CreditCardDtoToCreditCard implements Converter<CreditCardDto, CreditCard> {
    @Override
    public CreditCard convert(CreditCardDto dto) {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(dto.getId());
        creditCard.setCardNumber(dto.getCardNumber());
        creditCard.setCvsNumber(dto.getCvsNumber());
        creditCard.setExpirationDate(dto.getExpirationDate());
        creditCard.setCustomerId(dto.getCustomerId());
        return creditCard;
    }
}
