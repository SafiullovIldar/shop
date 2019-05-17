package com.shop.demo.converter.dtotoentity;

import com.shop.demo.dto.PromoCodeDto;
import com.shop.demo.entity.PromoCode;
import org.springframework.core.convert.converter.Converter;

public class PromoCodeDtoToPromoCode implements Converter<PromoCodeDto, PromoCode> {

    @Override
    public PromoCode convert(PromoCodeDto promoCodeDto) {
        PromoCode promoCode = new PromoCode();
        promoCode.setId(promoCodeDto.getId());
        promoCode.setExpirationDate(promoCodeDto.getExpirationDate());
        promoCode.setDiscount(promoCodeDto.getDiscount());
        promoCode.setItemId(promoCodeDto.getItemId());
        return promoCode;
    }
}
