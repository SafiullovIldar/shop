package com.shop.demo.service;

import com.shop.demo.config.AppConfig;
import com.shop.demo.dao.PurchaseDao;
import com.shop.demo.dao.ShoppingCartDao;
import com.shop.demo.dto.ShoppingCartDto;
import com.shop.demo.entity.ShoppingCart;
import com.shop.demo.util.Util;
import org.hamcrest.CoreMatchers;
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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class ShoppingCartServiceTest {

    @Autowired
    private ConversionService conversionService;

    @Mock
    private PurchaseDao purchaseDao;

    @Mock
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    @InjectMocks
    private ShoppingCartService shoppingCartService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnShoppingCartWithExpectedShoppingCartId() {
        ShoppingCartDto shoppingCartDto = Util.createTestShoppingCart("1");
        ShoppingCart shoppingCart = conversionService.convert(shoppingCartDto, ShoppingCart.class);

        when(shoppingCartDao.getShoppingCartById("1")).thenReturn(shoppingCart);

        assertEquals("1", shoppingCartService.getShoppingCartDtoById("1").getId());
    }

    @Test
    public void shouldPrintCorrectMessageDependingOnPaymentMethod() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        shoppingCartService.pay("1", "1");
        assertThat(outContent.toString(), containsString("paid using credit card"));

        shoppingCartService.pay("1", "2");
        assertThat(outContent.toString(), containsString("paid using PayPal"));

        shoppingCartService.pay("1", "3");
        assertThat(outContent.toString(), containsString("There is no payment information"));

    }
}