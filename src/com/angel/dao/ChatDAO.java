package com.angel.dao;

import com.angel.dto.ChatDTO;
import com.angel.dto.ChatListDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatDAO {
    private static ChatDAO chatDAO = new ChatDAO();
    private ChatDAO(){}

    public static ChatDAO getChatDAO() {
        return chatDAO;
    }

    public int findChat(Connection conn, int productNo, String sessionID) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT u.userNo AS buyerNo          ");
        sql.append(" FROM users u INNER JOIN chat c      ");
        sql.append("    ON c.writer = u.userID           ");
        sql.append("    INNER JOIN products p            ");
        sql.append("    ON c.productNo = p.productNo     ");
        sql.append("    INNER JOIN users u2              ");
        sql.append("    ON p.sellerNo = u2.userNo        ");
        sql.append(" WHERE c.productNo = ?               ");
        sql.append("       AND c.writer = ?              ");
        sql.append(" LIMIT 0,1                           ");
        ResultSet rs = null;
        int result = 0;
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setInt(1,productNo);
            pstmt.setString(2,sessionID);
            rs = pstmt.executeQuery();
            while(rs.next()){
                result = rs.getInt("buyerNo");
            }
        }finally {
            if(rs!=null)try{rs.close();}catch (Exception e){}
        }
        return result;
    }

    public void insertChat(Connection conn, ChatDTO dto) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO chats ( productNo  ");
        sql.append("                     ,content   ");
        sql.append("                     ,writer    ");
        sql.append("                     ,buyerNo ) ");
        sql.append(" VALUES ( ? ,? ,? ,? )          ");
        int result = 0;
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setInt(1,dto.getProductNo());
            pstmt.setString(2,dto.getContent());
            pstmt.setString(3,dto.getWriter());
            pstmt.setInt(4,dto.getBuyerNo());
            pstmt.executeUpdate();
        }
    }

    public List<ChatDTO> findChatList(Connection conn, int bno, int pno) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT content      ");
        sql.append("        , writer     ");
        sql.append(" FROM chat           ");
        sql.append(" WHERE buyerNo = ?   ");
        sql.append("   AND productNo = ? ");
        ArrayList<ChatDTO> list = new ArrayList<>();
        ResultSet rs = null;
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setInt(1,bno);
            pstmt.setInt(2,pno);
            rs = pstmt.executeQuery();
            while(rs.next()){
                ChatDTO dto = new ChatDTO();
                dto.setWriter(rs.getString("writer"));
                dto.setContent(rs.getString("content"));
                list.add(dto);
            }
        }finally {
            if(rs!=null)try{rs.close();}catch (Exception e){}
        }
        return list;
    }

    public boolean isSeller(Connection conn, String sessionId)throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT COUNT(*) AS countMsg       ");
        sql.append(" FROM chat c INNER JOIN products p ");
        sql.append("   ON c.productNo = p.productNo    ");
        sql.append("   INNER JOIN users u              ");
        sql.append("   ON p.sellerNo = u.userNo        ");
        sql.append(" WHERE u.userID = ?                ");
        ResultSet rs = null;
        boolean isSeller = false;
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setString(1,sessionId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                if(rs.getInt("countMsg")!=0) {
                    isSeller = true;
                }
            }
        }finally {
            if(rs!=null)try{rs.close();}catch (Exception e){}
        }
        return isSeller;
    }

    public int findBuyerNo(Connection conn, String sessionID) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT userNo    ");
        sql.append(" FROM users       ");
        sql.append(" WHERE userID = ? ");
        ResultSet rs = null;
        int result = 0;
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setString(1,sessionID);
            rs = pstmt.executeQuery();
            while(rs.next()){
                result = rs.getInt(0);
            }
        }finally {
            if(rs!=null)try{rs.close();}catch (Exception e){}
        }
        return result;
    }
}
