package com.shop.demo.service;

import com.shop.demo.config.AppConfig;
import com.shop.demo.dao.PurchaseDao;
import com.shop.demo.dto.PurchaseDto;
import com.shop.demo.entity.Purchase;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class OrderServiceTest {

    @Autowired
    private ConversionService conversionService;

    @Mock
    private PurchaseDao purchaseDao;

    @Autowired
    @InjectMocks
    private OrderService orderService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnPurchaseWithExpectedPurchaseId() {
        PurchaseDto purchaseDto = Util.createTestPurchase("1");
        Purchase purchase = conversionService.convert(purchaseDto, Purchase.class);

        when(purchaseDao.getPurchaseById("1", "1")).thenReturn(purchase);

        assertEquals("1", orderService.getPurchaseById("1", "1").getId());
    }

    @Test
    public void ShouldGetListOfPurchasesWithExpectedPurchasesCount() {
        List<PurchaseDto> purchaseDtos = Util.createTestPurchases();
        List<Purchase> purchases = new ArrayList<>();
        for (PurchaseDto purchase : purchaseDtos) {
            purchases.add(conversionService.convert(purchase, Purchase.class));
        }

        when(purchaseDao.getPurchases("1")).thenReturn(purchases);

        assertEquals(2, orderService.getPurchases("1").size());
    }
}