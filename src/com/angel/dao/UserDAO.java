package com.angel.dao;

import com.angel.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private static UserDAO dao = new UserDAO();

    public static UserDAO getDao(){return dao;}

    private UserDAO(){}


    public void loginUser(Connection conn, UserDTO dto) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append(" select userID, password            ");
        sql.append(" from users                           ");
        sql.append(" where userID = ? and password = ?  ");

        ResultSet rs = null;
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            )
        {
            pstmt.setString(1, dto.getUserID());
            pstmt.setString(2, dto.getPassword());
            rs = pstmt.executeQuery();

            while(rs.next()){
                dto.setUserID(rs.getString("userID"));
                dto.setPassword(rs.getString("password"));
            }
        }
    }

    public void joinUser(Connection conn, UserDTO dto) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into users ( userID        ");
        sql.append("                   , password      ");
        sql.append("                   , userName      ");
        sql.append("                   , address)      ");
        sql.append(" values (?, ?, ?, ? )              ");

        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        )
        {
            pstmt.setString(1, dto.getUserID());
            pstmt.setString(2, dto.getPassword());
            pstmt.setString(3, dto.getUserName());
            pstmt.setString(4, dto.getAddress());

            pstmt.executeUpdate();
        }
    }
}
