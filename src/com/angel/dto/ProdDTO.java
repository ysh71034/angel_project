package com.angel.dto;

import java.time.LocalDate;

public class ProdDTO {
    private int productNo;
    private String productName;
    private String description;
    private int price;
    private int sellerNo;
    private int categoryNo;
    private LocalDate registerDate;

    public ProdDTO(){}
    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(int sellerNo) {
        this.sellerNo = sellerNo;
    }

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }
}
