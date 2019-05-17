package com.shop.demo.dao;

import com.shop.demo.entity.Purchase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PurchaseDao {

    void createPurchase(Purchase purchase);
    Purchase getPurchaseById(String customerId, String purchaseId);
    List<Purchase> getPurchases(String customerId);
    List<Purchase> getPurchaseHistory(String customerId);

}
