package com.shop.demo.dao;

import com.shop.demo.entity.ShoppingCart;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public interface ShoppingCartDao {

    ShoppingCart getShoppingCartById(String shoppingCartId);
    void createShoppingCart(ShoppingCart shoppingCart, String customer_id) throws SQLException;
}
