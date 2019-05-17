package com.shop.demo.config;

import com.shop.demo.converter.dtotoentity.*;
import com.shop.demo.converter.entitytodto.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new PromoCodeDtoToPromoCode());
        registry.addConverter(new CatalogDtoToCatalog());
        registry.addConverter(new CustomerDtoToCustomer());
        registry.addConverter(new ItemDtoToItem());
        registry.addConverter(new PurchaseDtoToPurchase());
        registry.addConverter(new ShoppingCartDtoToShoppingCart());
        registry.addConverter(new CompanyDtoToCompany());
        registry.addConverter(new CreditCardDtoToCreditCard());

        registry.addConverter(new PromoCodeToPromoCodeDto());
        registry.addConverter(new CatalogToCatalogDto());
        registry.addConverter(new CustomerToCustomerDto());
        registry.addConverter(new ItemToItemDto());
        registry.addConverter(new PurchaseToPurchaseDto());
        registry.addConverter(new ShoppingCartToShoppingCartDto());
        registry.addConverter(new CompanyToCompanyDto());
        registry.addConverter(new CreditCardToCreditCardDto());
    }
}

