package com.angel.service;

import com.angel.comm.DBConnection;
import com.angel.dao.UserDAO;
import com.angel.dto.ChatDTO;
import com.angel.dto.UserDTO;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public class UserService {

    private static UserService instance = new UserService();

    public static UserService getInstance() {return instance;}

    private UserService(){}


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
}
