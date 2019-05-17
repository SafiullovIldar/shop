package com.shop.demo.service.impl;

import com.shop.demo.dao.PromoCodeDao;
import com.shop.demo.dto.PromoCodeDto;
import com.shop.demo.entity.PromoCode;
import com.shop.demo.service.PromoCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PromoCodeServiceImpl implements PromoCodeService {

    private final PromoCodeDao promoCodeDao;
    private final ConversionService conversion;

    @Override
    public PromoCodeDto getPromoCode(String promoCodeId) {
        PromoCode promoCode = promoCodeDao.getPromoCode(promoCodeId);
        PromoCodeDto promoCodeDto = conversion.convert(promoCode, PromoCodeDto.class);

        return promoCodeDto;
    }

    @Override
    public void createPromoCode(PromoCodeDto dto) {
        PromoCode promoCode = conversion.convert(dto, PromoCode.class);
        promoCodeDao.createPromoCode(promoCode);
    }
}
