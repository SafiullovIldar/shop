package com.shop.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDto {

    private String id;
    private String cardNumber;
    private Date expirationDate;
    private Long cvsNumber;
    private String customerId;
}