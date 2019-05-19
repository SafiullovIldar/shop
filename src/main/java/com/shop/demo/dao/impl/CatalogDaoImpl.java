package com.shop.demo.dao.impl;


import com.shop.demo.dao.CatalogDao;
import com.shop.demo.entity.Catalog;
import com.shop.demo.transaction.TransactionalManager;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CatalogDaoImpl implements CatalogDao {

    private HikariDataSource dataSource;
    private TransactionalManager transactionalManager;

    @Override
    public void createCatalog(Catalog catalog) throws SQLException {

        Connection connection = transactionalManager.getConnection();

        PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO catalog VALUES (?,?,?)");

        preparedStatement.setString(1, catalog.getId());
        preparedStatement.setString(2, catalog.getName());
        preparedStatement.setString(3, "1");

        preparedStatement.executeUpdate();

    }

    @Override
    public void deleteCatalog(String catalogId) throws SQLException {

        Connection connection = transactionalManager.getConnection();

        PreparedStatement preparedStatement = connection
                .prepareStatement("DELETE FROM catalog WHERE id = ?");

        preparedStatement.setString(1, catalogId);

        preparedStatement.executeUpdate();

    }

    @Override
    public List<Catalog> getAllCatalogs() {

        List<Catalog> catalogs = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM catalog");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Catalog catalog = new Catalog();
                catalog.setId(resultSet.getString("id"));
                catalog.setName(resultSet.getString("name"));
                catalogs.add(catalog);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return catalogs;
    }

    @Override
    public Catalog getCatalogById(String catalogId) {
        Catalog catalog = null;

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM catalog WHERE id = ?");

            preparedStatement.setString(1, catalogId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                catalog = new Catalog();
                catalog.setId(resultSet.getString("id"));
                catalog.setName(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return catalog;
    }
}
