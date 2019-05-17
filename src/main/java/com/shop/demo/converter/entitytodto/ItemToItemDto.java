package com.shop.demo.converter.entitytodto;

import com.shop.demo.dto.ItemDto;
import com.shop.demo.entity.Item;
import org.springframework.core.convert.converter.Converter;

public class ItemToItemDto implements Converter<Item, ItemDto> {
    @Override
    public ItemDto convert(Item item) {
        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setAvailability(item.getAvailability());
        dto.setPrice(item.getPrice());
        return dto;
    }
}
