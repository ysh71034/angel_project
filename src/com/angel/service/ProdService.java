package com.angel.service;

import com.angel.comm.DBConnection;
import com.angel.dao.ImageDAO;
import com.angel.dao.ProdDAO;
import com.angel.dto.ImageDTO;
import com.angel.dto.ProdDTO;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProdService {
    private static ProdService service = new ProdService();
    public static ProdService getService() {
        return service;
    }
    private ProdService(){}


    public void insertProd(ProdDTO dto, List<ImageDTO> imgList) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        try {
            conn = db.getConnection();
            conn.setAutoCommit(false);
            ProdDAO dao = ProdDAO.getDAO();
            ImageDAO dao2 = ImageDAO.getDAO();

            dao.insertProd(conn,dto);

            int productNO = dto.getProductNo();
            for(ImageDTO dto2 : imgList){
                dto2.setProductNo(productNO);
                dao2.insertImg(conn,dto2);
            }
            conn.commit();
        }catch (Exception e){
            System.out.println(e);
            try {
                conn.rollback();
            }catch (Exception e2){}
        }finally {
            if(conn!=null)try {
                conn.close();
            }catch (Exception e){}
        }
    }

    public int findSellerNo(String sessionID) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        int sellerNo= 0;
        try {
            conn = db.getConnection();
            conn.setAutoCommit(false);
            ProdDAO dao = ProdDAO.getDAO();
            sellerNo = dao.findSellerNo(conn,sessionID);


            conn.commit();
        }catch (Exception e){
            System.out.println(e);
            try {
                conn.rollback();
            }catch (Exception e2){}
        }finally {
            if(conn!=null)try {
                conn.close();
            }catch (Exception e){}
        }
        return sellerNo;
    }

    public ProdDTO detailProd(int productNo) {
        Connection conn = null;
        DBConnection db = DBConnection.getDbConn();
        ProdDAO dao = ProdDAO.getDAO();
        ProdDTO dto = new ProdDTO();

        try {
            conn = db.getConnection();
            dto = dao.detailProd(conn,productNo);
        }catch (SQLException | NamingException e){
            System.out.println(e);
        }
        return dto;
    }

    public int modProd(ProdDTO dto, List<ImageDTO> imgList) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        int result = 0;
        try {
            conn=db.getConnection();
            conn.setAutoCommit(false);
            ProdDAO dao = ProdDAO.getDAO();
            ImageDAO dao2 = ImageDAO.getDAO();
            //상품 수정(이미지 제외한 부분)
            result = dao.modProd(conn,dto);
            int productNo = dto.getProductNo();
            for(ImageDTO dto2:imgList){
                dto2.setProductNo(productNo);
                // 이미지 수정
                dao2.modImg(conn,dto2);
            }
            conn.commit();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            if(conn!=null)try {
                conn.close();
            }catch (Exception e){}
        }
        return result;
    }
}
