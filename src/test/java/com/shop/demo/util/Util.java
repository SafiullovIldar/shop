package com.shop.demo.util;

import com.shop.demo.dto.*;
import com.shop.demo.enums.PurchaseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Util {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ConversionService conversionService;

    public static CreditCardDto createTestCreditCard(String creditCardId, String customerId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse ("2019-12-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CreditCardDto creditCard = new CreditCardDto();
        creditCard.setId(creditCardId);
        creditCard.setCardNumber("12345");
        creditCard.setCvsNumber(555L);
        creditCard.setExpirationDate(date);
        creditCard.setCustomerId(customerId);
        return creditCard;
    }

    public static CompanyDto createTestCompany(String companyId) {
        CompanyDto dto = new CompanyDto();
        dto.setId(companyId);
        dto.setName("TestCompany");
        return dto;
    }

    public static List<PurchaseDto> createTestPurchases() {
        List<PurchaseDto> purchases = new ArrayList<>();

        PurchaseDto purchase1 = createTestPurchase("1");
        PurchaseDto purchase2 = createTestPurchase("2");

        purchases.add(purchase1);
        purchases.add(purchase2);

        return purchases;
    }

    public static PurchaseDto createTestPurchase(String purchaseId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse ("2019-12-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PurchaseDto dto = new PurchaseDto();
        dto.setId(purchaseId);
        dto.setShoppingCartId("1");
        dto.setPromoCodeId("1");
        dto.setItemId("1");
        dto.setStatus(PurchaseStatus.INIT);
        dto.setAmount(5);
        dto.setCreateDate(date);
        return dto;
    }

    public static ShoppingCartDto createTestShoppingCart(String shoppingCartId) {
        ShoppingCartDto dto = new ShoppingCartDto();
        dto.setId(shoppingCartId);
        dto.setPurchases(createTestPurchases());
        return dto;
    }

    public static PromoCodeDto createTestPromoCode(String promoCodeId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
           date = format.parse ("2019-12-31");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        PromoCodeDto dto = new PromoCodeDto();
        dto.setId(promoCodeId);
        dto.setItemId("1");
        dto.setExpirationDate(date);
        dto.setDiscount(10f);
        return dto;
    }

    public static CustomerDto createTestCustomer(String customerId) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customerId);
        dto.setName("TestCustomer");
        dto.setPhoneNumber("TestNumber");
        dto.setEmail("TestEmail");
        dto.setAddress("TestAddress");
        return dto;
    }

    public static CatalogDto createTestCatalog(String catalogId, String firstItemId, String secondItemId) {
        CatalogDto dto = new CatalogDto();
        dto.setId(catalogId);
        dto.setName("Test");
        dto.setItems(createTestItems(firstItemId, secondItemId));

        return dto;
    }

    public static ItemDto createTestItem(String itemId) {
        ItemDto item = new ItemDto();
        item.setId(itemId);
        item.setPrice(150);
        item.setAvailability(50);
        item.setName("Test1");
        return item;
    }

    public static List<ItemDto> createTestItems(String firstItemId, String secondItemId) {
        List<ItemDto> items = new ArrayList<>();

        ItemDto item1 = createTestItem(firstItemId);
        ItemDto item2 = createTestItem(secondItemId);

        items.add(item1);
        items.add(item2);

        return items;
    }
}
