package com.shop.demo.dao;

import com.shop.demo.entity.Catalog;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public interface CatalogDao {

    void createCatalog(Catalog catalog) throws SQLException;
    void deleteCatalog(String catalogId) throws SQLException;
    List<Catalog> getAllCatalogs();
    Catalog getCatalogById(String catalogId);
}
