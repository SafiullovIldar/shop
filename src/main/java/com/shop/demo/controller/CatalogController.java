package com.shop.demo.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.demo.dto.CatalogDto;
import com.shop.demo.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("catalog")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CatalogController {

    private final CatalogService catalogService;

    @PostMapping
    public void createCatalog(@RequestBody CatalogDto dto) {
        catalogService.createCatalog(dto);
    }

    @PostMapping("/file")
    public void createCatalogFromFile(@RequestParam("file") MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        CatalogDto catalogDto = objectMapper.readValue(file.getInputStream(), CatalogDto.class);

        catalogService.createCatalog(catalogDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCatalog(@PathVariable("id") String catalogId) {
        catalogService.deleteCatalog(catalogId);
    }

    @GetMapping
    public List<CatalogDto> getAllCatalogs() {
        return catalogService.getAllCatalogs();
    }

    @GetMapping("/{id}")
    public CatalogDto getCatalogById(@PathVariable("id") String catalogId) {
        return catalogService.getCatalogById(catalogId);
    }
}
