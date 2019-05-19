package com.shop.demo.service;

import com.shop.demo.config.AppConfig;
import com.shop.demo.dao.CreditCardDao;
import com.shop.demo.dto.CompanyDto;
import com.shop.demo.dto.CreditCardDto;
import com.shop.demo.entity.Company;
import com.shop.demo.entity.CreditCard;
import com.shop.demo.util.Util;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class CreditCardServiceTest {

    @Autowired
    private ConversionService conversionService;

    @Mock
    private CreditCardDao creditCardDao;

    @Autowired
    @InjectMocks
    private CreditCardService creditCardService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenCreateCreditCardCalledVerified() {

        CreditCardDto creditCardDto = Util.createTestCreditCard("1", "1");
        CreditCard creditCard = conversionService.convert(creditCardDto, CreditCard.class);

        doNothing().when(creditCardDao).createCreditCard(creditCard);
        creditCardService.createCreditCard(creditCardDto);

        verify(creditCardDao, times(1)).createCreditCard(creditCard);
    }
}