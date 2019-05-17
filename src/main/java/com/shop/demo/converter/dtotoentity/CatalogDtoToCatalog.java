package com.shop.demo.converter.dtotoentity;

import com.shop.demo.dto.CatalogDto;
import com.shop.demo.dto.ItemDto;
import com.shop.demo.entity.Catalog;
import com.shop.demo.entity.Item;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;


public class CatalogDtoToCatalog implements Converter<CatalogDto, Catalog> {

    @Override
    public Catalog convert(CatalogDto catalogDto) {
        ItemDtoToItem dtoToItem = new ItemDtoToItem();
        Catalog catalog = new Catalog();
        catalog.setId(catalogDto.getId());
        catalog.setName(catalogDto.getName());

        List<Item> items = new ArrayList<>();
        List<ItemDto> itemDtos = catalogDto.getItems();

        for (ItemDto itemDto : itemDtos) {
            items.add(dtoToItem.convert(itemDto));
        }

        catalog.setItems(items);
        return catalog;
    }
}
