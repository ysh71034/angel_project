package com.angel.dto;

public class ChatEnterDTO {
    private int buyerNo;
    private String buyerID;
    private String sellerID;

    public int getBuyerNo() {
        return buyerNo;
    }

    public void setBuyerNo(int buyerNo) {
        this.buyerNo = buyerNo;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

}
