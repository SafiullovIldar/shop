package com.shop.demo.entity;

import com.shop.demo.enums.PurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    private String id;
    private Date createDate;
    private PurchaseStatus status = PurchaseStatus.INIT;
    private Integer amount;
    private String itemId;
    private String shoppingCartId;
    private String promoCodeId;

}
