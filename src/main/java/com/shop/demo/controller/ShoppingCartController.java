package com.shop.demo.controller;

import com.shop.demo.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/shopping_cart")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;

    @PostMapping("/payment")
    public void pay(@RequestParam("customer_id") String customerId,
                    @RequestParam("payment_method") String paymentMethod) {
        shoppingCartService.pay(customerId, paymentMethod);
    }
}
