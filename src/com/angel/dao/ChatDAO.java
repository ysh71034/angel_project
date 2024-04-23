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

    public ChatEnterDTO findChat(Connection conn, int productNo, String sessionID) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT u.userNo AS chat_buyerNo     ");
        sql.append("        ,u.userID AS chat_buyerID    ");
        sql.append("        ,u2.userID AS chat_sellerID  ");
        sql.append(" FROM users u INNER JOIN chat c      ");
        sql.append("    ON c.writer = u.userID           ");
        sql.append("    INNER JOIN products p            ");
        sql.append("    ON c.productNo = p.productNo     ");
        sql.append("    INNER JOIN users u2              ");
        sql.append("    ON p.sellerNo = u2.userNo        ");
        sql.append(" WHERE c.productNo = ?               ");
        sql.append("       AND c.content = ?             ");
        ResultSet rs = null;
        ChatEnterDTO enterDTO = new ChatEnterDTO();
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setInt(1,productNo);
            pstmt.setString(2,sessionID+" 님이 입장하셨습니다.");
            rs = pstmt.executeQuery();
            while(rs.next()){
                enterDTO.setBuyerNo(rs.getInt("chat_buyerNo"));
                enterDTO.setBuyerID(rs.getString("chat_buyerID"));
                enterDTO.setSellerID("chat_sellerID");
            }
        }finally {
            if(rs!=null)try{rs.close();}catch (Exception e){}
        }
        return enterDTO;
    }

    public void insertChat(Connection conn, int productNo, String sessionID) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO chats ( productNo ");
        sql.append("                     ,content  ");
        sql.append("                     ,writer ) ");
        sql.append(" VALUES (? ,? ,?)              ");
        int result = 0;
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setInt(1,productNo);
            pstmt.setString(2,sessionID+" 님이 입장하셨습니다.");
            pstmt.setString(3,sessionID);
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
}
