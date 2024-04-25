package com.angel.dao;

import com.angel.dto.ImageDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void delImg(Connection conn, int productNo) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("  delete  from         ");
        sql.append("   images              ");
        sql.append("  where  productNo  = ? ");
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            pstmt.setInt(1,productNo);
            pstmt.executeUpdate();
        }
    }

    public String getImgPath(Connection conn, int productNo) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append(" select   imagePath ");
        sql.append("  from images       ");
        sql.append(" where productNo = ? ");
        ResultSet rs = null;
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            pstmt.setInt(1,productNo);
            rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getString("imagePath");
            }
        }
        return "";
    }
}
