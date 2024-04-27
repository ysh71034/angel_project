package com.angel.dao;

import com.angel.dto.ChatDTO;
import com.angel.dto.ChatListDTO;
import com.angel.dto.RoomDTO;

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
        sql.append(" INSERT INTO chat ( productNo   ");
        sql.append("                     ,content   ");
        sql.append("                     ,writer    ");
        sql.append("                     ,buyerNo ) ");
        sql.append(" VALUES ( ? ,? ,? ,? )          ");
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

    public boolean isSeller(Connection conn, int pno,String sessionId)throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT u.userID AS sellerID        ");
        sql.append(" FROM products p INNER JOIN users u ");
        sql.append("   ON p.sellerNo = u.userNo         ");
        sql.append(" WHERE p.productNo = ?              ");
        ResultSet rs = null;
        boolean isSeller = false;
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setInt(1,pno);
            rs = pstmt.executeQuery();
            while(rs.next()){
                if(rs.getString("sellerID").equals(sessionId)) {
                    isSeller = true;
                }
            }
        }finally {
            if(rs!=null)try{rs.close();}catch (Exception e){}
        }
        return isSeller;
    }

    public int findUserNo(Connection conn, String sessionID) throws SQLException{
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
                result = rs.getInt("userNo");
            }
        }finally {
            if(rs!=null)try{rs.close();}catch (Exception e){}
        }
        return result;
    }

    public void deleteChat(Connection conn, int pno, int bno) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM chat    ");
        sql.append(" WHERE productNo = ? ");
        sql.append("   AND buyerNo <> ?   ");
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setInt(1,pno);
            pstmt.setInt(2,bno);
            pstmt.executeUpdate();
        }
    }

    public List<RoomDTO> findSellerRoomList(Connection conn, int pno) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT r.roomNo                        ");
        sql.append("        , r.productNo                   ");
        sql.append("        , r.buyerNo                     ");
        sql.append("        , u.userID AS buyerID           ");
        sql.append(" FROM chatroom r inner join users u     ");
        sql.append("   ON r.buyerNo = u.userNo              ");
        sql.append(" WHERE r.productNo = ?                  ");
        ResultSet rs = null;
        ArrayList<RoomDTO> list = new ArrayList<>();
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setInt(1,pno);
            rs = pstmt.executeQuery();
            while(rs.next()){
                RoomDTO room = new RoomDTO();
                room.setRoomNo(rs.getInt("roomNo"));
                room.setProductNo(rs.getInt("productNo"));
                room.setBuyerNo(rs.getInt("buyerNo"));
                room.setBuyerID(rs.getString("buyerID"));
                list.add(room);
            }
        }finally {
            if(rs!=null)try{rs.close();}catch (Exception e){}
        }
        return list;
    }

    public void insertRoom(Connection conn, int productNo, int getBno) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append(" INSERT INTO chatroom ( productNo   ");
        sql.append("                        ,buyerNo )  ");
        sql.append(" VALUES ( ? ,? )                    ");
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setInt(1,productNo);
            pstmt.setInt(2,getBno);
            pstmt.executeUpdate();
        }
    }

    public int findRoom(Connection conn, int productNo) throws SQLException{
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT buyerNo      ");
        sql.append(" FROM chatroom       ");
        sql.append(" WHERE productNo = ? ");
        ResultSet rs = null;
        int result = 0;
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setInt(1,productNo);
            rs = pstmt.executeQuery();
            while(rs.next()){
                result = rs.getInt("buyerNo");
            }
        }finally {
            if(rs!=null)try{rs.close();}catch (Exception e){}
        }
        return result;
    }

    public void deleteRoom(Connection conn, int pno, int bno) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE FROM chatroom ");
        sql.append(" WHERE productNo = ?  ");
        sql.append("   AND buyerNo <> ?   ");
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setInt(1,pno);
            pstmt.setInt(2,bno);
            pstmt.executeUpdate();
        }
    }

    public List<RoomDTO> findBuyerRoomList(Connection conn, int myno)throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT r.roomNo AS roomNo             ");
        sql.append("        ,r.productNo AS productNo      ");
        sql.append("        ,p.productName AS productName  ");
        sql.append(" FROM chatroom r INNER JOIN products p ");
        sql.append("   ON r.productNo = p.productNo        ");
        sql.append(" WHERE r.buyerNo = ?                   ");
        ResultSet rs = null;
        ArrayList<RoomDTO> list = new ArrayList<>();
        try(PreparedStatement pstmt = conn.prepareStatement(sql.toString())){
            pstmt.setInt(1,myno);
            rs = pstmt.executeQuery();
            while(rs.next()){
                RoomDTO room = new RoomDTO();
                room.setRoomNo(rs.getInt("roomNo"));
                room.setProductNo(rs.getInt("productNo"));
                room.setProductName(rs.getString("productName"));
                list.add(room);
            }
        }finally {
            if(rs!=null)try{rs.close();}catch (Exception e){}
        }
        return list;
    }
}
