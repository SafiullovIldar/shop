package com.shop.demo.converter.entitytodto;

import com.shop.demo.dto.PurchaseDto;
import com.shop.demo.entity.Purchase;
import org.springframework.core.convert.converter.Converter;

public class PurchaseToPurchaseDto implements Converter<Purchase, PurchaseDto> {

    // TODO: 26.04.2019

    @Override
    public PurchaseDto convert(Purchase purchase) {

        PurchaseDto dto = new PurchaseDto();
        dto.setId(purchase.getId());
        dto.setAmount(purchase.getAmount());
        dto.setCreateDate(purchase.getCreateDate());
        dto.setStatus(purchase.getStatus());
        dto.setItemId(purchase.getItemId());
        dto.setPromoCodeId(purchase.getPromoCodeId());
        dto.setShoppingCartId(purchase.getShoppingCartId());

        return dto;
    }
}
