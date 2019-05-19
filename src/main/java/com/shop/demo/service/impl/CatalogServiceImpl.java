package com.shop.demo.service.impl;

import com.shop.demo.dao.CatalogDao;
import com.shop.demo.dto.CatalogDto;
import com.shop.demo.dto.ItemDto;
import com.shop.demo.entity.Catalog;
import com.shop.demo.service.CatalogService;
import com.shop.demo.service.ItemService;
import com.shop.demo.transaction.TransactionalManager;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private CatalogDao catalogDao;
    private ItemService itemService;
    private ConversionService conversion;
    private TransactionalManager transactionalManager;

    @Override
    public void createCatalog(CatalogDto dto) {
        Catalog catalog = conversion.convert(dto, Catalog.class);
        if (dto.getId() == null) {
            catalog.setId(UUID.randomUUID().toString());
        }
        transactionalManager.startTransaction();

        try {
            catalogDao.createCatalog(catalog);
            itemService.createItems(dto.getItems(), catalog.getId());
            transactionalManager.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            transactionalManager.rollBack();
        }
    }

    @Override
    public void deleteCatalog(String catalogId) {
        List<ItemDto> catalogItems = itemService.getItemsFromCatalog(catalogId);

        transactionalManager.startTransaction();

        try {
            for (ItemDto catalogItem : catalogItems) {
                itemService.deleteItem(catalogItem.getId());
            }

            catalogDao.deleteCatalog(catalogId);
            transactionalManager.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            transactionalManager.rollBack();
        }


    }

    @Override
    public List<CatalogDto> getAllCatalogs() {
        List<Catalog> allCatalogs = catalogDao.getAllCatalogs();
        List<CatalogDto> catalogDtos = new ArrayList<>();

        for (Catalog catalog : allCatalogs) {
            catalogDtos.add(conversion.convert(catalog, CatalogDto.class));
        }

        return catalogDtos;
    }

    @Override
    public CatalogDto getCatalogById(String catalogId) {
        Catalog catalog = catalogDao.getCatalogById(catalogId);
        CatalogDto catalogDto = conversion.convert(catalog, CatalogDto.class);

        List<ItemDto> itemsDto = itemService.getItemsFromCatalog(catalogId);
        if (catalogDto != null) {
            catalogDto.setItems(itemsDto);
        }

        return catalogDto;
    }
}
