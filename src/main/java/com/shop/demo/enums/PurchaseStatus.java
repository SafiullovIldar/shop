package com.shop.demo.enums;

public enum PurchaseStatus {

    INIT("init"),
    COMPLETED("completed");

    private String value;

    PurchaseStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
