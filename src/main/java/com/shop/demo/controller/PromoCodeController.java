package com.shop.demo.controller;


import com.shop.demo.dto.PromoCodeDto;
import com.shop.demo.service.PromoCodeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("promocode")
public class PromoCodeController {

    private PromoCodeService promoCodeService;

    @GetMapping("/{id}")
    public PromoCodeDto getPromoCode(@PathVariable("id") String promoCodeId) {
        return promoCodeService.getPromoCode(promoCodeId);
    }

    @PostMapping
    public void createPromoCode(@RequestBody PromoCodeDto dto) {
        promoCodeService.createPromoCode(dto);
    }
}
