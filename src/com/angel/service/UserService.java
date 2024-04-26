package com.angel.service;

import com.angel.comm.DBConnection;
import com.angel.dao.ChatDAO;
import com.angel.dao.UserDAO;
import com.angel.dto.ChatDTO;
import com.angel.dto.InfoDTO;
import com.angel.dto.UserDTO;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public class UserService {

    private static UserService instance = new UserService();

    public static UserService getInstance() {return instance;}

    private UserService(){}

    public int findMyNo(String sessionId) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        ChatDAO dao = ChatDAO.getChatDAO();
        int result = 0;
        try{
            conn = db.getConnection();
            result = dao.findUserNo(conn,sessionId);
        }catch (SQLException | NamingException e){
            System.out.println("findMyNo "+e);
        }finally{
            if(conn!=null)try{conn.close();} catch (Exception e){}
        }
        return result;
    }


    public UserDTO loginUser(String userID, String password) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        UserDAO dao = UserDAO.getDao();
        UserDTO dto = new UserDTO();

        try{
            conn = db.getConnection();
            dto = dao.loginUser(conn, userID, password);
        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally{
            if(conn!=null)try{conn.close();} catch (Exception e){}
        }
        return dto;
    }


    public int joinUser(UserDTO dto) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        UserDAO dao = UserDAO.getDao();

        int result = 0;
        try{
            conn = db.getConnection();
            result = dao.joinUser(conn, dto);

        }catch (SQLException | NamingException e){
            System.out.println("joinUser: "+e);
        }finally{
            if(conn!=null)try{conn.close();} catch (Exception e){}
        }
        return result;
    }

    public void modUser(UserDTO dto, int uno) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        UserDAO dao = UserDAO.getDao();

        try{
            conn = db.getConnection();
            dao.modUser(conn, dto, String.valueOf(uno));

        }catch (SQLException | NamingException e){
            System.out.println("joinUser: "+e);
        }finally{
            if(conn!=null)try{conn.close();} catch (Exception e){}
        }
    }

    public void deleteUser(int uno) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        UserDAO dao = UserDAO.getDao();

        try{
            conn = db.getConnection();
            dao.deleteUser(conn, String.valueOf(uno));

        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally{
            if(conn!=null)try{conn.close();} catch (Exception e){}
        }
    }

    public boolean findUser(String uid) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        UserDAO dao = UserDAO.getDao();

        boolean result = false;
        try{
            conn = db.getConnection();
            result = dao.findUser(conn, uid);

        }catch (SQLException | NamingException e){
            System.out.println("findUser "+e);
        }finally{
            if(conn!=null)try{conn.close();} catch (Exception e){}
        }
        return result;

    }

    public void addSellCount(String sessionId) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        UserDAO dao = UserDAO.getDao();
        try{
            conn = db.getConnection();
            dao.addSellCount(conn,sessionId);
        }catch (SQLException | NamingException e){
            System.out.println("addSellCount "+e);
        }finally{
            if(conn!=null)try{conn.close();} catch (Exception e){}
        }
    }

    public InfoDTO findMyInfo(int myno) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        UserDAO dao = UserDAO.getDao();
        InfoDTO info = new InfoDTO();
        try{
            conn = db.getConnection();
            info = dao.findMyInfo(conn,myno);
        }catch (SQLException | NamingException e){
            System.out.println("addSellCount "+e);
        }finally{
            if(conn!=null)try{conn.close();} catch (Exception e){}
        }
        return info;
    }

}
