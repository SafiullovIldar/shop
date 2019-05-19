package com.shop.demo.service.impl;

import com.shop.demo.service.ItemService;
import com.shop.demo.service.OrderService;
import com.shop.demo.service.PromoCodeService;
import com.shop.demo.service.ShoppingCartService;
import com.shop.demo.dao.PurchaseDao;
import com.shop.demo.dto.ItemDto;
import com.shop.demo.dto.PromoCodeDto;
import com.shop.demo.dto.PurchaseDto;
import com.shop.demo.dto.ShoppingCartDto;
import com.shop.demo.entity.Purchase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private PurchaseDao purchaseDao;
    private ItemService itemService;
    private ConversionService conversion;
    private ShoppingCartService shoppingCartService;
    private PromoCodeService promoCodeService;

    @Override
    public void createPurchase(String customerId,
                               String itemId,
                               Integer amount,
                               String catalogId,
                               String promoCodeId) {

        Purchase purchase = new Purchase();
        purchase.setId(UUID.randomUUID().toString());

        purchase.setAmount(amount);
        purchase.setCreateDate(new Date());

        PromoCodeDto promoCodeDto = promoCodeService.getPromoCode(promoCodeId);
        if (promoCodeDto != null) {
            purchase.setPromoCodeId(promoCodeId);
        } else {
            log.info("There is no such a promoCode");
        }

        ItemDto itemDto = itemService.getItemById(catalogId, itemId);
        purchase.setItemId(itemDto.getId());

        ShoppingCartDto shoppingCartDtoById = shoppingCartService.getShoppingCartDtoById(customerId);
        purchase.setShoppingCartId(shoppingCartDtoById.getId());

        purchaseDao.createPurchase(purchase);

    }

    @Override
    public List<PurchaseDto> getPurchases(String customerId) {
        List<Purchase> purchases = purchaseDao.getPurchases(customerId);
        List<PurchaseDto> purchaseDtos = new ArrayList<>();

        for (Purchase purchase : purchases) {
            purchaseDtos.add(conversion.convert(purchase, PurchaseDto.class));
        }

        return purchaseDtos;
    }

    @Override
    public PurchaseDto getPurchaseById(String customerId,
                                       String purchaseId) {

        Purchase purchaseById = purchaseDao.getPurchaseById(customerId, purchaseId);
        PurchaseDto purchaseDto = conversion.convert(purchaseById, PurchaseDto.class);

        return purchaseDto;
    }

    @Override
    public List<PurchaseDto> getPurchaseHistory(String customerId) {
        List<Purchase> purchaseHistory = purchaseDao.getPurchaseHistory(customerId);
        List<PurchaseDto> purchaseDtos = new ArrayList<>();


        for (Purchase purchase : purchaseHistory) {
            purchaseDtos.add(conversion.convert(purchase, PurchaseDto.class));
        }

        return purchaseDtos;
    }
}
