package com.angel.service;

import com.angel.comm.DBConnection;
import com.angel.dao.ImageDAO;
import com.angel.dao.ProdDAO;
import com.angel.dto.ImageDTO;
import com.angel.dto.ProdDTO;

import javax.naming.NamingException;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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

            int productNo = dto.getProductNo();
            for(ImageDTO dto2 : imgList){
                dto2.setProductNo(productNo);
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
            System.out.println("update"+productNo);
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

    public List<ProdDTO> sellerProd(int sellerNo) {
        Connection conn = null;
        DBConnection db = DBConnection.getDbConn();
        ProdDAO dao = ProdDAO.getDAO();
        List<ProdDTO> sellerprod = new ArrayList<>();
        try {
            conn = db.getConnection();
            sellerprod = dao.sellerProd(conn,sellerNo);
        }catch (SQLException | NamingException e){
            System.out.println(e);
        }
        return sellerprod;
    }

    public List<ProdDTO> catProd(int categoryNo) {
        Connection conn = null;
        DBConnection db = DBConnection.getDbConn();
        ProdDAO dao = ProdDAO.getDAO();
        List<ProdDTO> catprod = new ArrayList<>();
        try {
            conn = db.getConnection();
            catprod = dao.catProd(conn,categoryNo);
        }catch (SQLException | NamingException e){
            System.out.println(e);
        }
        return catprod;
    }

    public void delProd(int productNo, String img) {
        Connection conn = null;
        DBConnection db = DBConnection.getDbConn();
        ProdDAO dao = ProdDAO.getDAO();
        ImageDAO dao2 = ImageDAO.getDAO();
        try {
            conn = db.getConnection();
            conn.setAutoCommit(false);
            dao2.delImg(conn,productNo);
            dao.delProd(conn,productNo);

            File f = new File(img);
            if(f.isFile()){
                if (f.exists()) {
                    f.delete();
                }
            }
            conn.commit();
        }catch (SQLException | NamingException e){
            System.out.println(e);
            try {
                conn.rollback();
            }catch (Exception e2){}
        }finally {
            db.disconn(conn);
        }
    }

    public ImageDTO imgPath(int productNo) {
        Connection conn = null;
        DBConnection db = DBConnection.getDbConn();
        ImageDTO dto = new ImageDTO();
        ImageDAO dao2 = ImageDAO.getDAO();
        try {
            conn= db.getConnection();
            String imagePath=dao2.getImgPath(conn,productNo);
            dto.setImagepath(imagePath);
        }catch (SQLException | NamingException e){
            System.out.println(e);
        }
        return dto;
    }


    public List<ProdDTO> prodList(int catNo) {
        Connection conn = null;
        DBConnection db = DBConnection.getDbConn();
        ProdDAO dao = ProdDAO.getDAO();

        List<ProdDTO> arr = new ArrayList<>();
        try {
            conn= db.getConnection();
            arr = dao.prodList(conn, catNo);

        }catch (SQLException | NamingException e){
            System.out.println(e);
        }
        return arr;
    }

    public List<ProdDTO> brandNewList() {
        Connection conn = null;
        DBConnection db = DBConnection.getDbConn();
        ProdDAO dao = ProdDAO.getDAO();

        List<ProdDTO> arr = new ArrayList<>();
        try {
            conn= db.getConnection();
            arr = dao.brandNewList(conn);

        }catch (SQLException | NamingException e){
            System.out.println(e);
        }
        return arr;
    }

    public void contract(int pno, int bno) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        ProdDAO dao = ProdDAO.getDAO();
        try{
            conn = db.getConnection();
            dao.insertOrder(conn,pno,bno);
        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally {
            if(conn!=null)try{conn.close();}catch (Exception e){}
        }
    }


}
