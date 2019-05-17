package com.shop.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartDto {

    private String id;
    private List<PurchaseDto> purchases;
}
