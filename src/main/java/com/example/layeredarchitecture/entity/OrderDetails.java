package com.example.layeredarchitecture.entity;

import java.math.BigDecimal;

public class OrderDetails {
    private String oid;
    private String itemCode;
    private BigDecimal unitPrice;
    private int qty;

    public OrderDetails() {
    }

    public OrderDetails(String oid, String itemCode, BigDecimal unitPrice, int qty) {
        this.oid = oid;
        this.itemCode = itemCode;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
