package com.shop.demo.dao;

import com.shop.demo.entity.Item;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public interface ItemDao {

    void deleteItem(String itemId) throws SQLException;
    void createItem(Item item, String catalogId);
    void createItems(List<Item> items, String catalogId) throws SQLException;
    List<Item> getItemsFromCatalog(String catalogId) ;
    Item getItemById(String catalogId, String itemId);
    Item getItemById(String itemId);

}
