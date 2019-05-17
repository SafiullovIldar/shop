package com.shop.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoCode {

    private String id;
    private Date expirationDate;
    private Float discount;
    private String itemId;
}
