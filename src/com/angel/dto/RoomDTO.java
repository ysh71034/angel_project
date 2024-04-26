package com.angel.dto;

public class RoomDTO {
    private int roomNo;
    private int productNo;
    private int buyerNo;

    private String buyerID;
    private String productName;

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
