package com.shop.demo.converter.dtotoentity;

import com.shop.demo.dto.PurchaseDto;
import com.shop.demo.entity.Purchase;
import org.springframework.core.convert.converter.Converter;

public class PurchaseDtoToPurchase implements Converter<PurchaseDto, Purchase> {

    // TODO: 26.04.2019

    @Override
    public Purchase convert(PurchaseDto purchaseDto) {

        Purchase purchase = new Purchase();
        purchase.setId(purchaseDto.getId());
        purchase.setStatus(purchaseDto.getStatus());
        purchase.setAmount(purchaseDto.getAmount());
        purchase.setCreateDate(purchaseDto.getCreateDate());
        purchase.setItemId(purchaseDto.getItemId());
        purchase.setPromoCodeId(purchaseDto.getPromoCodeId());
        purchase.setShoppingCartId(purchaseDto.getShoppingCartId());

        return purchase;
    }
}
