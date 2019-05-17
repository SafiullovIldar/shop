package com.shop.demo.service;

import com.shop.demo.dto.PromoCodeDto;
import org.springframework.stereotype.Component;

@Component
public interface PromoCodeService {

    PromoCodeDto getPromoCode(String promoCodeId);
    void createPromoCode(PromoCodeDto dto);
}
