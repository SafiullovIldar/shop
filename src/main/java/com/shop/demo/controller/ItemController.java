package com.shop.demo.controller;

import com.shop.demo.dto.ItemDto;
import com.shop.demo.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/item")
public class ItemController {

    private ItemService itemService;

    @PostMapping()
    public void createItem(@RequestParam("catalog_id") String catalogId, @RequestBody ItemDto itemDto) {
        itemService.createItem(itemDto, catalogId);
    }

    @GetMapping()
    public List<ItemDto> getItemsFromCatalog(@RequestParam("catalog_id") String catalogId) {
        return itemService.getItemsFromCatalog(catalogId);
    }

    @GetMapping("/{item_id}")
    public ItemDto getItemById(@RequestParam("catalog_id") String catalogId,
                            @PathVariable("item_id") String itemId) {
        return itemService.getItemById(catalogId, itemId);
    }

}
