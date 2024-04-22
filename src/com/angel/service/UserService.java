package com.angel.service;

import com.angel.comm.DBConnection;
import com.angel.dao.UserDAO;
import com.angel.dto.UserDTO;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public class UserService {

    private static UserService instance = new UserService();

    public static UserService getInstance() {return instance;}

    private UserService(){}


    public void loginUser(UserDTO dto) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        UserDAO dao = UserDAO.getDao();

        try{
            conn = db.getConnection();
            dao.loginUser(conn, dto);

        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally{
            if(conn!=null)try{conn.close();} catch (Exception e){}
        }
    }


    public void joinUser(UserDTO dto) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        UserDAO dao = UserDAO.getDao();

        try{
            conn = db.getConnection();
            dao.joinUser(conn, dto);

        }catch (SQLException | NamingException e){
            System.out.println(e);
        }finally{
            if(conn!=null)try{conn.close();} catch (Exception e){}
        }
    }
}
