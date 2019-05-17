package com.shop.demo.dto;

import lombok.Data;

@Data
public class ItemDto {

    private String id;
    private String name;
    private Integer price;
    private Integer availability;
}
