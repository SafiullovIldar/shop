package com.shop.demo.dao.impl;

import com.shop.demo.dao.CreditCardDao;
import com.shop.demo.entity.CreditCard;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
@AllArgsConstructor
public class CreditCardDaoImpl implements CreditCardDao {

    private HikariDataSource dataSource;

    @Override
    public void createCreditCard(CreditCard creditCard) {

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO credit_card VALUES (?,?,?,?,?)");

            preparedStatement.setString(1, creditCard.getId());
            preparedStatement.setString(2, creditCard.getCardNumber());
            preparedStatement.setDate(3, new Date(creditCard.getExpirationDate().getTime()));
            preparedStatement.setLong(4, creditCard.getCvsNumber());
            preparedStatement.setString(5, creditCard.getCustomerId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CreditCard getCreditCard(String creditCardId) {
        CreditCard creditCard = null;
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM credit_card WHERE id = ?");

            preparedStatement.setString(1, creditCardId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                creditCard = new CreditCard();
                creditCard.setId(resultSet.getString("id"));
                creditCard.setCustomerId(resultSet.getString("customer_id"));
                creditCard.setExpirationDate(resultSet.getDate("expiration_date"));
                creditCard.setCvsNumber(resultSet.getLong("cvc"));
                creditCard.setCardNumber(resultSet.getString("card_number"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return creditCard;
    }

    @Override
    public CreditCard getCreditCardByCustomerId(String customerId) {
        CreditCard creditCard = null;
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM credit_card WHERE customer_id = ?");

            preparedStatement.setString(1, customerId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                creditCard = new CreditCard();
                creditCard.setId(resultSet.getString("id"));
                creditCard.setCustomerId(resultSet.getString("customer_id"));
                creditCard.setExpirationDate(resultSet.getDate("expiration_date"));
                creditCard.setCvsNumber(resultSet.getLong("cvc"));
                creditCard.setCardNumber(resultSet.getString("card_number"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return creditCard;
    }
}
