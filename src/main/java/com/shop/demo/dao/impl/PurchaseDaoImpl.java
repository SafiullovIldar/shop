package com.shop.demo.dao.impl;

import com.shop.demo.dao.PurchaseDao;
import com.shop.demo.entity.Purchase;
import com.shop.demo.enums.PurchaseStatus;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.shop.demo.enums.PurchaseStatus.COMPLETED;
import static com.shop.demo.enums.PurchaseStatus.INIT;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PurchaseDaoImpl implements PurchaseDao {

    private final HikariDataSource dataSource;

    public void createPurchase(Purchase purchase) {
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement(
                            "INSERT INTO purchase VALUES (?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, purchase.getId());
            preparedStatement.setDate(2, new Date(purchase.getCreateDate().getTime()));
            preparedStatement.setString(3, purchase.getStatus().getValue());
            preparedStatement.setInt(4, purchase.getAmount());
            preparedStatement.setString(5, purchase.getItemId());
            preparedStatement.setString(6, purchase.getPromoCodeId());
            preparedStatement.setString(7, purchase.getShoppingCartId());

            preparedStatement.executeUpdate();
        }
            catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public Purchase getPurchaseById(String customerId, String purchaseId) {
        Purchase purchase = null;

        try (Connection connection = dataSource.getConnection()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement(
                            "SELECT p.*" +
                                "  FROM purchase p," +
                                "       shopping_cart sc," +
                                "       customer c" +
                                " WHERE p.id = ?" +
                                "   AND p.cart_id = sc.id" +
                                "   AND sc.customer_id = c.id" +
                                "   AND c.id = ?;");

            preparedStatement.setString(1, customerId);
            preparedStatement.setString(2, purchaseId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                purchase = new Purchase();
                purchase.setId(resultSet.getString("id"));
                purchase.setCreateDate(resultSet.getDate("create_date"));
                purchase.setStatus(PurchaseStatus.valueOf(resultSet.getString("status").toUpperCase()));
                purchase.setAmount(resultSet.getInt("amount"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return purchase;
    }

    @Override
    public List<Purchase> getPurchases(String customerId) {
        List<Purchase> purchases = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement(
                            "SELECT p.*" +
                                "  FROM purchase p," +
                                "       shopping_cart sc," +
                                "       customer c" +
                                " WHERE p.cart_id = sc.id" +
                                "   AND sc.customer_id = c.id" +
                                "   AND c.id = ?" +
                                "   AND p.status = ?;");

            initPurchases(customerId, INIT, purchases, preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return purchases;
    }

    @Override
    public List<Purchase> getPurchaseHistory(String customerId) {
        List<Purchase> purchases = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement(
                            "SELECT p.*" +
                                "  FROM purchase p," +
                                "       shopping_cart sc," +
                                "       customer c\n" +
                                " WHERE p.cart_id = sc.id" +
                                "   AND sc.customer_id = c.id" +
                                "   AND c.id = ?" +
                                "   AND p.status = ?;");

            initPurchases(customerId, COMPLETED, purchases, preparedStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return purchases;
    }

    private void initPurchases(String customerId,
                               PurchaseStatus status,
                               List<Purchase> purchases,
                               PreparedStatement preparedStatement) throws SQLException {

        preparedStatement.setString(1, customerId);
        preparedStatement.setString(2, status.getValue());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Purchase purchase = new Purchase();
            purchase.setId(resultSet.getString("id"));
            purchase.setCreateDate(resultSet.getDate("create_date"));
            purchase.setStatus(PurchaseStatus.valueOf(resultSet.getString("status").toUpperCase()));
            purchase.setAmount(resultSet.getInt("amount"));
            purchase.setItemId(resultSet.getString("item_id"));
            purchase.setShoppingCartId(resultSet.getString("cart_id"));
            purchase.setPromoCodeId(resultSet.getString("promo_id"));
            purchases.add(purchase);
        }
    }
}
