package com.angel.dto;

public class ImageDTO {
    private int imageNo;
    private String imagepath;
    private int productNo;

    public ImageDTO() {
    }

    public ImageDTO(int imageNo, String imagepath, int productNo) {
        this.imageNo = imageNo;
        this.imagepath = imagepath;
        this.productNo = productNo;
    }

    public int getImageNo() {
        return imageNo;
    }

    public void setImageNo(int imageNo) {
        this.imageNo = imageNo;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }
}
