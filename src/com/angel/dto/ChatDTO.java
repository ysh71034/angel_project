package com.angel.dto;

public class ChatDTO {
    private int messegeNo;
    private int productNo;
    private String content;
    private String writer;

    public int getMessegeNo() {
        return messegeNo;
    }

    public void setMessegeNo(int messegeNo) {
        this.messegeNo = messegeNo;
    }

    public int getProductNo() {
        return productNo;
    }

    public void setProductNo(int productNo) {
        this.productNo = productNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
