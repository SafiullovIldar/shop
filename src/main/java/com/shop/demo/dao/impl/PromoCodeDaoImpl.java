package com.shop.demo.dao.impl;

import com.shop.demo.dao.PromoCodeDao;
import com.shop.demo.entity.PromoCode;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PromoCodeDaoImpl implements PromoCodeDao {

    private final HikariDataSource dataSource;

    @Override
    public PromoCode getPromoCode(String promoCodeId) {
        PromoCode promoCode = null;

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM promo_code WHERE id = ?");

            preparedStatement.setString(1, promoCodeId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                promoCode = new PromoCode();
                promoCode.setId(resultSet.getString("id"));
                promoCode.setExpirationDate(resultSet.getDate("expiration_date"));
                promoCode.setDiscount(resultSet.getFloat("discount"));
                promoCode.setItemId(resultSet.getString("item_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return promoCode;
    }

    @Override
    public void createPromoCode(PromoCode promoCode) {

        try (Connection connection = dataSource.getConnection()){

            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO promo_code VALUES (?,?,?,?)");

            preparedStatement.setString(1, promoCode.getId());
            preparedStatement.setDate(2, new Date(promoCode.getExpirationDate().getTime()));
            preparedStatement.setFloat(3, promoCode.getDiscount());
            preparedStatement.setString(4, promoCode.getItemId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
