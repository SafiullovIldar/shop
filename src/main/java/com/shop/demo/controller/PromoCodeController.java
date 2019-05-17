package com.shop.demo.controller;


import com.shop.demo.dto.PromoCodeDto;
import com.shop.demo.service.PromoCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("promocode")
public class PromoCodeController {

    private final PromoCodeService promoCodeService;

    @GetMapping("/{id}")
    public PromoCodeDto getPromoCode(@PathVariable("id") String promoCodeId) {
        return promoCodeService.getPromoCode(promoCodeId);
    }

    @PostMapping
    public void createPromoCode(@RequestBody PromoCodeDto dto) {
        promoCodeService.createPromoCode(dto);
    }
}
