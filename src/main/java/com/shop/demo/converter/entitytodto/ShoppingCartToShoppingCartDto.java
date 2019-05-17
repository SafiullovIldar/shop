package com.shop.demo.converter.entitytodto;

import com.shop.demo.dto.PurchaseDto;
import com.shop.demo.dto.ShoppingCartDto;
import com.shop.demo.entity.Purchase;
import com.shop.demo.entity.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartToShoppingCartDto implements Converter<ShoppingCart, ShoppingCartDto> {

    // TODO: 25.04.2019

    @Autowired
    private ConversionService conversion;

    @Override
    public ShoppingCartDto convert(ShoppingCart shoppingCart) {
        PurchaseToPurchaseDto toPurchaseDto = new PurchaseToPurchaseDto();
        ShoppingCartDto dto = new ShoppingCartDto();
        List<PurchaseDto> purchaseDtos = new ArrayList<>();
        List<Purchase> purchases = shoppingCart.getPurchases();

        if (purchases != null) {
            for (Purchase purchase : purchases) {
                purchaseDtos.add(toPurchaseDto.convert(purchase));
            }
        }

        dto.setId(shoppingCart.getId());
        dto.setPurchases(purchaseDtos);
        return dto;
    }
}
