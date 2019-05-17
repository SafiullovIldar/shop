package com.shop.demo.dao;


import com.shop.demo.entity.PromoCode;
import org.springframework.stereotype.Component;

@Component
public interface PromoCodeDao {

    PromoCode getPromoCode(String promoCodeId);
    void createPromoCode(PromoCode promoCode);

}
