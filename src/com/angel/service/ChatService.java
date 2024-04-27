package com.angel.service;

import com.angel.comm.DBConnection;
import com.angel.dao.ChatDAO;
import com.angel.dto.ChatDTO;
import com.angel.dto.ChatListDTO;
import com.angel.dto.RoomDTO;

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


    public int findChatRoom(int productNo, String sessionID) {
        // 이 메서드는 구매자에만 적용된다.
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        ChatDAO dao = ChatDAO.getChatDAO();
        ChatDTO dto = new ChatDTO();
        RoomDTO roomDTO = new RoomDTO();
        int buyerNo = -1;
        try{
            conn = db.getConnection();
            conn.setAutoCommit(false);
            // 이미 채팅에 입장했던 구매자인 경우 바로 buyerNo를 받는다.
            //buyerNo = dao.findChat(conn,productNo,sessionID);
            buyerNo = dao.findRoom(conn,productNo);
            // 채팅방에 최초로 입장한 구매자인 경우 채팅방 및 채팅 입장 메시지를 만들고 buyerNo를 받는다.
            if(buyerNo==0) {
                int getBno = dao.findUserNo(conn, sessionID);
                // 채팅방 추가
                dao.insertRoom(conn,productNo,getBno);
                // 채팅 입장 메시지 추가
                dto.setProductNo(productNo);
                dto.setContent(sessionID+" 님이 채팅에 참여하셨습니다.");
                dto.setWriter(sessionID);
                dto.setBuyerNo(getBno);
                dao.insertChat(conn,dto);
                //buyerNo = dao.findChat(conn,productNo,sessionID);
                buyerNo = dao.findRoom(conn,productNo);
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

    public boolean isSeller(int pno,String sessionId) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        ChatDAO dao = ChatDAO.getChatDAO();
        boolean isSeller = false;
        try{
            conn = db.getConnection();
            isSeller = dao.isSeller(conn,pno,sessionId);
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

    public void deleteChat(int pno, int bno) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        ChatDAO dao = ChatDAO.getChatDAO();
        try{
            conn = db.getConnection();
            dao.deleteChat(conn,pno,bno);
        }catch (SQLException|NamingException e){
            System.out.println(e.getMessage());
        }finally {
            if(conn!=null)try{conn.close();}catch (Exception e){}
        }
    }

    public List<RoomDTO> findSellerRoomList(int pno) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        ChatDAO dao = ChatDAO.getChatDAO();
        List<RoomDTO> list = new ArrayList<>();
        try{
            conn = db.getConnection();
            list = dao.findSellerRoomList(conn,pno);
        }catch (SQLException|NamingException e){
            System.out.println(e.getMessage());
        }finally {
            if(conn!=null)try{conn.close();}catch (Exception e){}
        }
        return list;
    }

    public void deleteRoom(int pno, int bno) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        ChatDAO dao = ChatDAO.getChatDAO();
        try{
            conn = db.getConnection();
            dao.deleteRoom(conn,pno,bno);
        }catch (SQLException|NamingException e){
            System.out.println(e.getMessage());
        }finally {
            if(conn!=null)try{conn.close();}catch (Exception e){}
        }
    }

    public List<RoomDTO> findBuyerRoomList(int myno) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        ChatDAO dao = ChatDAO.getChatDAO();
        List<RoomDTO> list = new ArrayList<>();
        try{
            conn = db.getConnection();
            list = dao.findBuyerRoomList(conn,myno);
        }catch (SQLException|NamingException e){
            System.out.println(e.getMessage());
        }finally {
            if(conn!=null)try{conn.close();}catch (Exception e){}
        }
        return list;
    }
}
