package com.shop.demo.controller;

import com.shop.demo.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/shopping_cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping("/payment")
    public void pay(@RequestParam("customer_id") String customerId,
                    @RequestParam("payment_method") String paymentMethod) {
        shoppingCartService.pay(customerId, paymentMethod);
    }
}
