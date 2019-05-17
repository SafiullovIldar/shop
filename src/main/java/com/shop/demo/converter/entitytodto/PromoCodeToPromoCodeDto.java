package com.shop.demo.converter.entitytodto;

import com.shop.demo.dto.PromoCodeDto;
import com.shop.demo.entity.PromoCode;
import org.springframework.core.convert.converter.Converter;

public class PromoCodeToPromoCodeDto implements Converter<PromoCode, PromoCodeDto> {
    @Override
    public PromoCodeDto convert(PromoCode promoCode) {
        PromoCodeDto dto = new PromoCodeDto();
        dto.setId(promoCode.getId());
        dto.setDiscount(promoCode.getDiscount());
        dto.setExpirationDate(promoCode.getExpirationDate());
        dto.setItemId(promoCode.getItemId());
        return dto;
    }
}
