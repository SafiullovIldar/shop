package com.shop.demo.converter.entitytodto;

import com.shop.demo.dto.CreditCardDto;
import com.shop.demo.entity.CreditCard;
import org.springframework.core.convert.converter.Converter;

public class CreditCardToCreditCardDto implements Converter<CreditCard, CreditCardDto> {

    @Override
    public CreditCardDto convert(CreditCard creditCard) {
        CreditCardDto dto = new CreditCardDto();
        dto.setId(creditCard.getId());
        dto.setCardNumber(creditCard.getCardNumber());
        dto.setCvsNumber(creditCard.getCvsNumber());
        dto.setExpirationDate(creditCard.getExpirationDate());
        dto.setCustomerId(creditCard.getCustomerId());
        return dto;
    }
}
