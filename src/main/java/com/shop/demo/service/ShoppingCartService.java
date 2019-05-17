package com.shop.demo.service;

import com.shop.demo.dto.ShoppingCartDto;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public interface ShoppingCartService {

    ShoppingCartDto getShoppingCartDtoById(String customerId);
    void createShoppingCart(ShoppingCartDto dto, String customerId) throws SQLException;
    void pay(String customerId, String paymentMethod);
}
