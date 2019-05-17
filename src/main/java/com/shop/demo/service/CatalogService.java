package com.shop.demo.service;

import com.shop.demo.dto.CatalogDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CatalogService {

    void createCatalog(CatalogDto dto);
    void deleteCatalog(String catalogId);
    List<CatalogDto> getAllCatalogs();
    CatalogDto getCatalogById(String catalogId);
}
