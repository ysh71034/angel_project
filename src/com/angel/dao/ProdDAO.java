package com.angel.dao;

import com.angel.dto.ImageDTO;
import com.angel.dto.OrderDTO;
import com.angel.dto.ProdDTO;
import com.angel.dto.UserDTO;
import com.sun.jna.platform.win32.COM.IConnectionPoint;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdDAO {
    public static ProdDAO dao = new ProdDAO();
    public ProdDAO(){}
    public static ProdDAO getDAO(){
        return dao;
    }

    public void insertProd(Connection conn, ProdDTO dto) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("  insert   into   products(  ");
        sql.append("   productName               ");
        sql.append("    ,description             ");
        sql.append("    ,price                   ");
        sql.append("    ,sellerNo               ");
        sql.append("    ,categoryNo             ");
        sql.append("    ,registerDate)             ");
        sql.append("     values(  ?,  ?,  ?, ?, ?, ?)     ");
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS)) {
            if(dto.getCategoryName().equals("book")){
                dto.setCategoryNo(1);
            }else if(dto.getCategoryName().equals("furniture")){
                dto.setCategoryNo(2);
            }else if(dto.getCategoryName().equals("req")){
                dto.setCategoryNo(3);
            }else if(dto.getCategoryName().equals("party")){
                dto.setCategoryNo(4);
            }else{
                dto.setCategoryNo(5);
            }

            pstmt.setString(1,dto.getProductName());
            pstmt.setString(2,dto.getDescription());
            pstmt.setInt(3,dto.getPrice());
            pstmt.setInt(4,dto.getSellerNo());
            pstmt.setInt(5,dto.getCategoryNo());
            pstmt.setDate(6,dto.getRegisterDate());
            pstmt.executeUpdate();

            try(ResultSet gen = pstmt.getGeneratedKeys()) {
                if(gen.next()){
                    dto.setProductNo(gen.getInt(1));
                }
            }
        }
    }

    public int findSellerNo(Connection conn,String sessionID) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("   select   userNo   ");
        sql.append("   from   users      ");
        sql.append(" where  userID =  ?  ");
        int result = 0;
        ResultSet rs = null;
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            pstmt.setString(1,sessionID);
            rs = pstmt.executeQuery();
            while (rs.next()){
                result = rs.getInt("userNo");
            }
        }
        return result;
    }


    public ProdDTO detailProd(Connection conn, int productNo) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append(" select  p.productNo         ");
        sql.append("       ,p.productName         ");
        sql.append(" ,p.description               ");
        sql.append("  ,p.price, c.categoryName    ");
        sql.append("  ,i.imagePath                ");
        sql.append("   ,u.userName                ");
        sql.append("   ,p.sellerNo                ");
        sql.append("   ,c.categoryNo              ");
        sql.append("  from  images i  inner join  ");
        sql.append("  products p   on             ");
        sql.append("  i.productNo = p.productNo   ");
        sql.append("   inner join  categories  c  ");
        sql.append("   on  c.categoryNo = p.categoryNo");
        sql.append("   inner join users u            ");
        sql.append("   on  u.userNo = p.sellerNo   ");
        sql.append("  where  p.productNo =  ?      ");
        ResultSet rs = null;
        ProdDTO dto = new ProdDTO();
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            pstmt.setInt(1,productNo);
            rs = pstmt.executeQuery();
            while (rs.next()){
                dto.setProductNo(rs.getInt("productNo"));
                dto.setProductName(rs.getString("productName"));
                dto.setDescription(rs.getString("description"));
                dto.setPrice(rs.getInt("price"));
                dto.setCategoryName(rs.getString("categoryName"));
                ImageDTO imgdto = new ImageDTO();
                imgdto.setImagepath(rs.getString("imagePath"));
                dto.setDto2(imgdto);
                UserDTO userdto = new UserDTO();
                userdto.setUserName(rs.getString("userName"));
                dto.setUserdto(userdto);
                dto.setSellerNo(rs.getInt("sellerNo"));
                dto.setCategoryNo(rs.getInt("categoryNo"));
            }
        }finally {
            if(rs!=null)try {
                rs.close();
            }catch (Exception e){}
        }
        return dto;
    }

    public int modProd(Connection conn, ProdDTO dto) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("   update    products    ");
        sql.append("   set  productName  =  ?");
        sql.append("    ,description   =  ?   ");
        sql.append("    ,price   =    ?       ");
        sql.append("   ,categoryNo  =   ?     ");
        sql.append("  where  productNo = ?    ");
        int result = 0;
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            if(dto.getCategoryName().equals("book")){
                dto.setCategoryNo(1);
            }else if(dto.getCategoryName().equals("furniture")){
                dto.setCategoryNo(2);
            }else if(dto.getCategoryName().equals("req")){
                dto.setCategoryNo(3);
            }else if(dto.getCategoryName().equals("party")){
                dto.setCategoryNo(4);
            }else{
                dto.setCategoryNo(5);
            }
            pstmt.setString(1,dto.getProductName());
            pstmt.setString(2,dto.getDescription());
            pstmt.setInt(3,dto.getPrice());
            pstmt.setInt(4,dto.getCategoryNo());
            pstmt.setInt(5,dto.getProductNo());
            result = pstmt.executeUpdate();
        }
        return result;
    }
// 판매자의 다른 상품 조회
    public List<ProdDTO> sellerProd(Connection conn, int sellerNo) throws SQLException{
        StringBuilder sql =new StringBuilder();
        sql.append("  select  p.productNo     ");
        sql.append("         ,p.productName   ");
        sql.append("   ,i.imagePath            ");
        sql.append("  from  images   i   inner join ");
        sql.append("  products  p                  ");
        sql.append("   on  i.productNo  = p.productNo ");
        sql.append("  where  p.sellerNo   =  ?        ");
        sql.append("    and  p.productNo not in ( select productNo ");
        sql.append("                              from orders ) ");
        sql.append("  limit  2");
        ResultSet rs = null;
        List<ProdDTO> sellerprod = new ArrayList<>();
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            pstmt.setInt(1,sellerNo);
            rs = pstmt.executeQuery();
            while (rs.next()){
                ProdDTO sellerdto = new ProdDTO();
                sellerdto.setProductNo(rs.getInt("productNo"));
                sellerdto.setProductName(rs.getString("productName"));
                ImageDTO dto2 = new ImageDTO();
                dto2.setImagepath(rs.getString("imagePath"));
                sellerdto.setDto2(dto2);
                sellerprod.add(sellerdto);
            }
        }finally {
            if(rs!=null)try {
                rs.close();
            }catch (Exception e){}
        }
        return sellerprod;
    }

    public List<ProdDTO> catProd(Connection conn, int categoryNo) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append(" select  p.productNo       ");
        sql.append("          ,p.productName    ");
        sql.append("    ,i.imagePath           ");
        sql.append("    ,c.categoryName        ");
        sql.append(" from  images i inner join ");
        sql.append(" products p         on     ");
        sql.append(" i.productNo = p.productNo ");
        sql.append(" inner  join  categories c ");
        sql.append(" on c.categoryNo  = p.categoryNo");
        sql.append("   where  c.categoryNo  = ? ");
        sql.append("     and p.productNo not in ( select procuctNo ");
        sql.append("                               from orders ) ");
        sql.append("  limit  2");
        ResultSet rs = null;
        List<ProdDTO> catprod = new ArrayList<>();
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            pstmt.setInt(1,categoryNo);
            rs = pstmt.executeQuery();
            while (rs.next()){
                ProdDTO catdto = new ProdDTO();
                catdto.setProductNo(rs.getInt("productNo"));
                catdto.setProductName(rs.getString("productName"));

                ImageDTO dto2 = new ImageDTO();
                dto2.setImagepath(rs.getString("imagePath"));

                catdto.setDto2(dto2);
                catprod.add(catdto);
            }
        }finally {
            if(rs!=null)try {
                rs.close();
            }catch (Exception e){}
        }
        return catprod;
    }

    public void delProd(Connection conn, int productNo) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("  delete  from         ");
        sql.append("  products             ");
        sql.append("  where    productNo = ?");
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            pstmt.setInt(1,productNo);
            pstmt.executeUpdate();
        }
    }

    public List<ProdDTO> prodList(Connection conn, int categoryNo) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append(" select p.productNo, p.productName, p.price, i.imagePath ");
        sql.append(" from images i inner join products p                     ");
        sql.append("        on i.productNo = p.productNo                     ");
        sql.append("      inner join categories c                            ");
        sql.append("        on c.categoryNo = p.categoryNo                   ");
        sql.append(" where c.categoryNo = ?                                  ");
        sql.append("  and p.productNo not in ( select productNo from orders )");

        ResultSet rs = null;
        List<ProdDTO> arr = new ArrayList<>();
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setInt(1, categoryNo);
            rs = pstmt.executeQuery();
            while(rs.next()){
                ProdDTO dto = new ProdDTO();
                dto.setProductNo(rs.getInt("productNo"));
                dto.setProductName(rs.getString("productName"));
                dto.setPrice(rs.getInt("price"));

                ImageDTO dto2 = new ImageDTO();
                dto2.setImagepath(rs.getString("imagePath"));
                dto.setDto2(dto2);
                arr.add(dto);
            }
        }finally {
            if(rs!=null)try {
                rs.close();
            }catch (Exception e){}
        }
        return arr;
    }

    public List<ProdDTO> brandNewList(Connection conn) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append(" select p.productNo, i.imagePath             ");
        sql.append(" from images i inner join products p         ");
        sql.append("   on i.productNo = p.productNo              ");
        sql.append(" where p.productNo not in ( select productNo ");
        sql.append("                              from orders  ) ");
        sql.append(" order by p.registerDate desc                ");
        sql.append("           , productNo desc                  ");
        sql.append(" limit 0, 5                                  ");

        ResultSet rs = null;
        List<ProdDTO> arr = new ArrayList<>();
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            rs = pstmt.executeQuery();
            while(rs.next()){
                ProdDTO dto = new ProdDTO();
                dto.setProductNo(rs.getInt("productNo"));

                ImageDTO dto2 = new ImageDTO();
                dto2.setImagepath(rs.getString("imagePath"));
                dto.setDto2(dto2);
                arr.add(dto);
            }
        }finally {
            if(rs!=null)try{rs.close();}catch (Exception e){}
        }
        return arr;
    }

    public void insertOrder(Connection conn, int pno, int bno) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO orders ( productNo    ");
        sql.append("                      , buyerNo    ");
        sql.append("                      , orderDate )");
        sql.append(" VALUES ( ? ,? ,DATE(NOW())       )");
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setInt(1,pno);
            pstmt.setInt(2,bno);
            pstmt.executeUpdate();
        }
    }

    public List<OrderDTO> findOrderList(Connection conn, int myno) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT o.orderNo AS orderNo           ");
        sql.append("        ,p.productName AS productName  ");
        sql.append("        ,u.userName AS sellerName      ");
        sql.append("        ,o.orderDate AS orderDate      ");
        sql.append(" FROM orders o INNER JOIN products p   ");
        sql.append("   ON o.productNo = p.productNo        ");
        sql.append("   INNER JOIN users u                  ");
        sql.append("   ON p.sellerNo = u.userNo            ");
        sql.append(" WHERE o.buyerNo = ?                   ");
        ResultSet rs = null;
        List<OrderDTO> arr = new ArrayList<>();
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setInt(1, myno);
            rs = pstmt.executeQuery();
            while(rs.next()){
                OrderDTO dto = new OrderDTO();
                dto.setOrderNo(rs.getInt("orderNo"));
                dto.setProductName(rs.getString("productName"));
                dto.setSellerName(rs.getString("sellerName"));
                dto.setOrderDate(rs.getDate("orderDate").toLocalDate());
                arr.add(dto);
            }
        }finally {
            if(rs!=null)try {
                rs.close();
            }catch (Exception e){}
        }
        return arr;
    }
// 거래횟수 쿼리는 수정필요
    public ProdDTO sellerInfo(Connection conn,int sellerNo) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append("   select  u.userName");
        sql.append("    ,nvl(u.sellCount,0)  as cnt      ");
        sql.append("    ,MAX(c.categoryName) as ctg   ");
        sql.append("  from  users u inner join  ");
        sql.append("  products p   on         ");
        sql.append("  p.sellerNo = u.userNo      ");
        sql.append("  inner join  categories c  ");
        sql.append(" on p.categoryNo = c.categoryNo ");
        sql.append("  where  p.sellerNo =   ?");
        ResultSet rs = null;
        ProdDTO dto = new ProdDTO();
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            pstmt.setInt(1,sellerNo);
            rs= pstmt.executeQuery();
            while (rs.next()){
                UserDTO userdto = new UserDTO();
                userdto.setUserName(rs.getString("userName"));
                userdto.setSellCount(rs.getInt("cnt"));
                dto.setCategoryName(rs.getString("ctg"));
                dto.setUserdto(userdto);
            }
        }
        return dto;
    }
}
