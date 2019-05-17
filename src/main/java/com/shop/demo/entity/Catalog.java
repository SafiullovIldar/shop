package com.shop.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catalog {

    private String id;
    private String name;
    private List<Item> items;

    public Catalog(Catalog catalog) {
        this.name = catalog.getName();
        this.items = new ArrayList<>();
        List<Item> items = new ArrayList<>();

        if (catalog.getItems() != null) {
            for (Item catalogItem : catalog.getItems()) {
                items.add(new Item(catalogItem));
            }
        }

        this.items = items;
    }
}
