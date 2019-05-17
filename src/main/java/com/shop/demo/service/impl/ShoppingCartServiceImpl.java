package com.shop.demo.service.impl;

import com.shop.demo.dao.PurchaseDao;
import com.shop.demo.dao.ShoppingCartDao;
import com.shop.demo.dto.ItemDto;
import com.shop.demo.dto.PurchaseDto;
import com.shop.demo.dto.ShoppingCartDto;
import com.shop.demo.entity.Purchase;
import com.shop.demo.entity.ShoppingCart;
import com.shop.demo.enums.PurchaseStatus;
import com.shop.demo.payment.CreditCardStrategy;
import com.shop.demo.payment.PayPalStrategy;
import com.shop.demo.payment.PaymentStrategy;
import com.shop.demo.service.ItemService;
import com.shop.demo.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartDao shoppingCartDao;
    private final ConversionService conversion;
    private final PurchaseDao purchaseDao;
    private final ItemService itemService;

    @Override
    public ShoppingCartDto getShoppingCartDtoById(String customerId) {
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCartById(customerId);
        ShoppingCartDto shoppingCartDto = conversion.convert(shoppingCart, ShoppingCartDto.class);

        List<Purchase> purchases = purchaseDao.getPurchases(customerId);
        List<PurchaseDto> purchaseDtos = new ArrayList<>();

        if (purchases.size() != 0) {
            for (Purchase purchase : purchases) {
                purchaseDtos.add(conversion.convert(purchase, PurchaseDto.class));
            }

            shoppingCartDto.setPurchases(purchaseDtos);
        }

        return shoppingCartDto;
    }

    @Override
    public void createShoppingCart(ShoppingCartDto dto, String customerId) throws SQLException {
        ShoppingCart shoppingCart = conversion.convert(dto, ShoppingCart.class);
        if (dto.getId() == null) {
            shoppingCart.setId(UUID.randomUUID().toString());
        }

        shoppingCartDao.createShoppingCart(shoppingCart, customerId );
    }

    @Override
    public void pay(String customerId, String paymentMethod) {
        PaymentStrategy paymentStrategy = null;

        List<Purchase> purchases = purchaseDao.getPurchases(customerId);
        int amount = 0;

        for (Purchase purchase : purchases) {
            String itemId = purchase.getItemId();
            ItemDto item = itemService.getItemById(itemId);
            Integer price = item.getPrice();

            if (item.getAvailability() < purchase.getAmount()) {
                log.error("There is not enough availability for " + item.getName());
                continue;
            }

            amount += price * purchase.getAmount();

            item.setAvailability(item.getAvailability() - purchase.getAmount());
            purchase.setStatus(PurchaseStatus.COMPLETED);
        }

        if (paymentMethod.equals("1")){
            paymentStrategy = new CreditCardStrategy();
            paymentStrategy.pay(amount);
        } else if (paymentMethod.equals("2")){
            paymentStrategy = new PayPalStrategy();
            paymentStrategy.pay(amount);
        } else {
            log.error("There is no payment information");
        }
    }
}
