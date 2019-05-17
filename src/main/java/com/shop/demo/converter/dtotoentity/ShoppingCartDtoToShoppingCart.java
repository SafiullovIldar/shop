package com.shop.demo.converter.dtotoentity;

import com.shop.demo.dto.ShoppingCartDto;
import com.shop.demo.entity.ShoppingCart;
import org.springframework.core.convert.converter.Converter;

public class ShoppingCartDtoToShoppingCart implements Converter<ShoppingCartDto, ShoppingCart> {
    @Override
    public ShoppingCart convert(ShoppingCartDto shoppingCartDto) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(shoppingCartDto.getId());
        return shoppingCart;
    }
}
