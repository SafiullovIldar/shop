package com.shop.demo.dao.impl;

import com.shop.demo.dao.ItemDao;
import com.shop.demo.entity.Item;
import com.shop.demo.transaction.TransactionalManager;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ItemDaoImpl implements ItemDao {

    private final HikariDataSource dataSource;
    private final TransactionalManager transactionalManager;

    @Override
    public void deleteItem(String itemId) throws SQLException {

        Connection connection = dataSource.getConnection();

        PreparedStatement preparedStatement = connection
                .prepareStatement("DELETE FROM item WHERE id = ?");

        preparedStatement.setString(1, itemId);

        preparedStatement.executeUpdate();

    }

    @Override
    public void createItem(Item item, String catalogId) {
        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO item VALUES (?,?,?,?,?)");

            preparedStatement.setString(1, item.getId());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setInt(3, item.getPrice());
            preparedStatement.setInt(4, item.getAvailability());
            preparedStatement.setString(5, catalogId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createItems(List<Item> items, String catalogId) throws SQLException {

        Connection connection = transactionalManager.getConnection();

        PreparedStatement preparedStatement = connection
                .prepareStatement("INSERT INTO item VALUES (?,?,?,?,?)");

        for (Item item : items) {

            preparedStatement.setString(1, item.getId());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setInt(3, item.getPrice());
            preparedStatement.setInt(4, item.getAvailability());
            preparedStatement.setString(5, catalogId);

            preparedStatement.executeUpdate();
        }

    }

    @Override
    public List<Item> getItemsFromCatalog(String catalog_id) {

        List<Item> items = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM item WHERE catalog_id = ?");
            preparedStatement.setString(1, catalog_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Item item = new Item();
                item.setId(resultSet.getString("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getInt("price"));
                item.setAvailability(resultSet.getInt("availability"));
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }

    @Override
    public Item getItemById(String catalog_id, String item_id) {

        Item item = null;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM item WHERE catalog_id = ? AND id = ? ");

            preparedStatement.setString(1, catalog_id);
            preparedStatement.setString(2, item_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                item = new Item();
                item.setId(resultSet.getString("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getInt("price"));
                item.setAvailability(resultSet.getInt("availability"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    @Override
    public Item getItemById(String item_id) {
        Item item = null;

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT * FROM item WHERE id = ? ");

            preparedStatement.setString(1, item_id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                item = new Item();
                item.setId(resultSet.getString("id"));
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getInt("price"));
                item.setAvailability(resultSet.getInt("availability"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

}
