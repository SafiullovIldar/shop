package com.shop.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class CatalogDto {

    private String id;
    private String name;
    private List<ItemDto> items;
}
