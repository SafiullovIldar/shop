package com.shop.demo.converter.dtotoentity;

import com.shop.demo.dto.ItemDto;
import com.shop.demo.entity.Item;
import org.springframework.core.convert.converter.Converter;

public class ItemDtoToItem implements Converter<ItemDto, Item> {

    @Override
    public Item convert(ItemDto itemDto) {
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setPrice(itemDto.getPrice());
        item.setAvailability(itemDto.getAvailability());
        return item;
    }
}
