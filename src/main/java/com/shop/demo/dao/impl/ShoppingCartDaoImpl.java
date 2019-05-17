package com.shop.demo.dao.impl;

import com.shop.demo.dao.ShoppingCartDao;
import com.shop.demo.entity.ShoppingCart;
import com.shop.demo.transaction.TransactionalManager;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    private final TransactionalManager transactionalManager;
    private final HikariDataSource dataSource;

    @Override
    public ShoppingCart getShoppingCartById(String customerId) {
        ShoppingCart shoppingCart = null;
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM shopping_cart WHERE customer_id = ?");

            preparedStatement.setString(1, customerId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                shoppingCart = new ShoppingCart();
                shoppingCart.setId(resultSet.getString("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return shoppingCart;
    }

    @Override
    public void createShoppingCart(ShoppingCart shoppingCart, String customerId) throws SQLException {
        Connection connection = transactionalManager.getConnection();

        PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO shopping_cart VALUES (?, ?)");

        preparedStatement.setString(1, shoppingCart.getId());
        preparedStatement.setString(2, customerId);

        preparedStatement.executeUpdate();
    }
}
