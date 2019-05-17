package com.shop.demo.dao;

import com.shop.demo.entity.Item;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public interface ItemDao {

    void deleteItem(String item_id) throws SQLException;
    void createItem(Item item, String catalogId);
    void createItems(List<Item> items, String catalogId) throws SQLException;
    List<Item> getItemsFromCatalog(String catalog_id) ;
    Item getItemById(String catalog_id, String item_id);
    Item getItemById(String item_id);

}
