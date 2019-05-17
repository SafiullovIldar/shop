package com.shop.demo.dto;

import com.shop.demo.enums.PurchaseStatus;
import lombok.Data;

import java.util.Date;

@Data
public class PurchaseDto {

    private String id;
    private Date createDate;
    private PurchaseStatus status = PurchaseStatus.INIT;
    private Integer amount;
    private String itemId;
    private String shoppingCartId;
    private String promoCodeId;
}
