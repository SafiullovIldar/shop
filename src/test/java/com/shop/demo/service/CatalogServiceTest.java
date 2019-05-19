package com.shop.demo.service;

import com.shop.demo.config.AppConfig;
import com.shop.demo.dao.CatalogDao;
import com.shop.demo.dto.CatalogDto;
import com.shop.demo.entity.Catalog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.SQLException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class CatalogServiceTest {

    @Autowired
    ConversionService conversionService;

    @Mock
    private CatalogDao catalogDao;

    @Mock
    private ItemService itemService;

    @Autowired
    @InjectMocks
    private CatalogService catalogService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetCatalogWithExpectedFields() {

        when(catalogDao.getCatalogById("1"))
                .thenReturn(new Catalog("100", "Testing", Collections.emptyList()));

        assertEquals("100", catalogService.getCatalogById("1").getId());
        assertEquals("Testing", catalogService.getCatalogById("1").getName());
        assertEquals(Collections.emptyList(), catalogService.getCatalogById("1").getItems());
    }

    @Test
    public void shouldGetExpectedCatalogsCountAndFields() {
        when(catalogDao.getAllCatalogs()).thenReturn(Collections.singletonList(
                new Catalog("4", "Test", Collections.emptyList())));

        assertEquals(1, catalogService.getAllCatalogs().size());
        assertEquals("4", catalogService.getAllCatalogs().get(0).getId());
        assertEquals("Test", catalogService.getAllCatalogs().get(0).getName());
    }

    @Test
    public void whenDeleteCatalogCalledVerified() throws SQLException {
        doNothing().when(catalogDao).deleteCatalog("1");
        catalogService.deleteCatalog("1");

        verify(catalogDao, times(1)).deleteCatalog("1");
    }

    @Test
    public void whenCreateCatalogCalledVerified() throws SQLException {
        Catalog catalog = new Catalog("5", "Test", Collections.emptyList());
        CatalogDto catalogDto = conversionService.convert(catalog, CatalogDto.class);

        doNothing().when(catalogDao).createCatalog(catalog);
        catalogService.createCatalog(catalogDto);

        verify(catalogDao, times(1)).createCatalog(catalog);
    }
}