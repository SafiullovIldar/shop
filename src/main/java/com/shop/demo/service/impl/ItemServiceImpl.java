package com.shop.demo.service.impl;

import com.shop.demo.dao.ItemDao;
import com.shop.demo.dto.ItemDto;
import com.shop.demo.entity.Item;
import com.shop.demo.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemDao itemDao;
    private ConversionService conversion;

    @Override
    public List<ItemDto> getItemsFromCatalog(String catalogId) {
        List<Item> itemsFromCatalog = itemDao.getItemsFromCatalog(catalogId);
        List<ItemDto> itemDtos = new ArrayList<>();

        for (Item item : itemsFromCatalog) {
            itemDtos.add(conversion.convert(item, ItemDto.class));
        }

        return itemDtos;
    }

    @Override
    public ItemDto getItemById(String catalogId, String itemId) {
        Item itemById = itemDao.getItemById(catalogId, itemId);
        ItemDto itemDto = conversion.convert(itemById, ItemDto.class);
        return itemDto;
    }

    @Override
    public ItemDto getItemById(String itemId) {
        Item itemById = itemDao.getItemById( itemId);
        ItemDto itemDto = conversion.convert(itemById, ItemDto.class);
        return itemDto;
    }

    @Override
    public void createItems(List<ItemDto> itemsDto, String catalogId) throws SQLException {
        List<Item> items = new ArrayList<>();
        if (itemsDto != null) {
            for (ItemDto itemDto : itemsDto) {
                items.add(conversion.convert(itemDto, Item.class));
            }
        }

        itemDao.createItems(items, catalogId);
    }

    @Override
    public void createItem(ItemDto itemDto, String catalogId) {
        Item item = conversion.convert(itemDto, Item.class);
        itemDao.createItem(item, catalogId);
    }

    @Override
    public void deleteItem(String itemId) throws SQLException {
        itemDao.deleteItem(itemId);
    }

}
