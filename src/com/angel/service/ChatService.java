package com.angel.service;

import com.angel.comm.DBConnection;
import com.angel.dao.ChatDAO;
import com.angel.dto.ChatDTO;
import com.angel.dto.ChatListDTO;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChatService {
    private static ChatService chatService = new ChatService();
    private ChatService(){}

    public static ChatService getChatService() {
        return chatService;
    }


    public int findChat(int productNo, String sessionID) {
        // 이 메서드는 구매자에만 적용된다.
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        ChatDAO dao = ChatDAO.getChatDAO();
        ChatDTO dto = new ChatDTO();
        int buyerNo = -1;
        try{
            conn = db.getConnection();
            conn.setAutoCommit(false);
            // 이미 채팅에 입장했던 구매자인 경우 바로 buyerNo를 받는다.
            buyerNo = dao.findChat(conn,productNo,sessionID);
            // 채팅방에 최초로 입장한 구매자인 경우 입장 메시지를 만들고 buyerNo를 받는다.
            if(buyerNo==0) {
                int getBno = dao.findUserNo(conn, sessionID);
                dto.setProductNo(productNo);
                dto.setContent(sessionID+" 님이 채팅에 참여하셨습니다.");
                dto.setWriter(sessionID);
                dto.setBuyerNo(getBno);
                dao.insertChat(conn,dto);
                buyerNo = dao.findChat(conn,productNo,sessionID);
            }
            conn.commit();
        }catch (SQLException | NamingException e){
            System.out.println(e.getMessage());
            try{conn.rollback();}catch (SQLException e2){
                System.out.println(e2.getMessage());
            }
        }finally {
            if(conn!=null)try{conn.close();}catch (Exception e){}
        }
        return buyerNo;
    }

    public List<ChatDTO> findChatList(int bno, int pno) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        ChatDAO dao = ChatDAO.getChatDAO();
        List<ChatDTO> list = new ArrayList<>();
        try{
            conn = db.getConnection();
            list = dao.findChatList(conn,bno,pno);
        }catch (SQLException|NamingException e){
            System.out.println(e.getMessage());
        }finally {
            if(conn!=null)try{conn.close();}catch (Exception e){}
        }
        return list;
    }

    public boolean isSeller(String sessionId) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        ChatDAO dao = ChatDAO.getChatDAO();
        boolean isSeller = false;
        try{
            conn = db.getConnection();
            isSeller = dao.isSeller(conn,sessionId);
        }catch (SQLException|NamingException e){
            System.out.println(e.getMessage());
        }finally {
            if(conn!=null)try{conn.close();}catch (Exception e){}
        }
        return isSeller;
    }

    public void insertChat(ChatDTO dto) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        ChatDAO dao = ChatDAO.getChatDAO();
        try{
            conn = db.getConnection();
            dao.insertChat(conn,dto);
        }catch (SQLException|NamingException e){
            System.out.println(e.getMessage());
        }finally {
            if(conn!=null)try{conn.close();}catch (Exception e){}
        }
    }

    public void deleteChat(int pno) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        ChatDAO dao = ChatDAO.getChatDAO();
        try{
            conn = db.getConnection();
            dao.deleteChat(conn,pno);
        }catch (SQLException|NamingException e){
            System.out.println(e.getMessage());
        }finally {
            if(conn!=null)try{conn.close();}catch (Exception e){}
        }
    }
}
