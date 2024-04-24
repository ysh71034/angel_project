package com.angel.dao;

import com.angel.dto.ImageDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImageDAO {
    public static ImageDAO dao = new ImageDAO();
    public ImageDAO(){}
    public static ImageDAO getDAO(){
        return dao;
    }

    public void insertImg(Connection conn, ImageDTO dto2) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("  insert   into   images(productNo  ");
        sql.append("      ,imagePath)                   ");
        sql.append("        values(?,?)                 ");
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            pstmt.setInt(1,dto2.getProductNo());
            pstmt.setString(2,dto2.getImagepath());
            pstmt.executeUpdate();
        }
    }

    public void modImg(Connection conn, ImageDTO dto2) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("  update   images            ");
        sql.append("  set   imagePath =   ?       ");
        sql.append("   where  productNo  =   ?    ");
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            pstmt.setString(1,dto2.getImagepath());
            pstmt.setInt(2,dto2.getProductNo());
            pstmt.executeUpdate();
        }
    }
}
