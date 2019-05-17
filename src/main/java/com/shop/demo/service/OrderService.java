package com.shop.demo.service;

import com.shop.demo.dto.PurchaseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderService {

    void createPurchase(String customerId,
                        String itemId,
                        Integer amount,
                        String catalogId,
                        String promoCodeId);
    List<PurchaseDto> getPurchases(String customerId);
    PurchaseDto getPurchaseById(String customerId, String purchaseId);
    List<PurchaseDto> getPurchaseHistory(String customerId);

}
