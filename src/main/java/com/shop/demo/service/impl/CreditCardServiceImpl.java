package com.shop.demo.service.impl;

import com.shop.demo.dao.CreditCardDao;
import com.shop.demo.dto.CreditCardDto;
import com.shop.demo.entity.CreditCard;
import com.shop.demo.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreditCardServiceImpl implements CreditCardService {

    private final ConversionService conversion;
    private final CreditCardDao creditCardDao;

    @Override
    public void createCreditCard(CreditCardDto dto) {
        CreditCard creditCard = conversion.convert(dto, CreditCard.class);
        creditCardDao.createCreditCard(creditCard);
    }

    @Override
    public CreditCardDto getCreditCard(String creditCardId) {
        CreditCard creditCard = creditCardDao.getCreditCard(creditCardId);
        CreditCardDto dto = conversion.convert(creditCard, CreditCardDto.class);
        return dto;
    }

    @Override
    public CreditCardDto getCreditCardByCustomerId(String customerId) {
        CreditCard creditCard = creditCardDao.getCreditCardByCustomerId(customerId);
        CreditCardDto dto = conversion.convert(creditCard, CreditCardDto.class);
        return dto;
    }
}
