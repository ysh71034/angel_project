package com.angel.dao;

import com.angel.dto.ChatDTO;
import com.angel.dto.ChatEnterDTO;

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
        sql.append(" SELECT u.userNo AS buyerNo     ");
//        sql.append("        ,u.userID AS chat_buyerID    ");
//        sql.append("        ,u2.userID AS chat_sellerID  ");
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
        sql.append(" SELECT c.content AS chat_content, ");
        sql.append("        , c.writer AS chat_writer  ");
        sql.append(" FROM chats c INNER JOIN users u   ");
        sql.append("   ON c.writer = u.userID          ");
        sql.append(" WHERE u.userNo = ?                ");
        sql.append("     AND c.productNo = ?           ");
        ArrayList<ChatDTO> list = new ArrayList<>();
        ResultSet rs = null;
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setInt(1,bno);
            pstmt.setInt(2,pno);
            pstmt.executeQuery();
            while(rs.next()){
                ChatDTO dto = new ChatDTO();
                dto.setContent(rs.getString("chat_content"));
                dto.setWriter(rs.getString("chat_writer"));
                list.add(dto);
            }
        }
        return list;
    }

    public boolean isSeller(Connection conn, String sessionId)throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT COUNT(*)                   ");
        sql.append(" FROM chat c INNER JOIN products p ");
        sql.append("   ON c.productNo = p.productNo    ");
        sql.append("   INNER JOIN users u              ");
        sql.append("   ON p.sellerNo = u.userNo        ");
        sql.append(" WHERE u.userID = ?                ");
        ResultSet rs = null;
        boolean isSeller = false;
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setString(1,sessionId);
            pstmt.executeQuery();
            while(rs.next()){
                if(rs.getInt(0)!=0)
                    isSeller = true;
            }
        }
        return true;
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
            pstmt.executeQuery();
            while(rs.next()){
                result = rs.getInt(0);
            }
        }
        return result;
    }
}
