package com.angel.service;

import com.angel.comm.DBConnection;
import com.angel.dao.ChatDAO;
import com.angel.dto.ChatDTO;
import com.angel.dto.ChatEnterDTO;

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

    public ChatEnterDTO findChat(int productNo, String sessionID) {
        DBConnection db = DBConnection.getDbConn();
        Connection conn = null;
        ChatDAO dao = ChatDAO.getChatDAO();
        ChatEnterDTO enterDTO = new ChatEnterDTO();
        try{
            conn = db.getConnection();
            enterDTO = dao.findChat(conn,productNo,sessionID);
            if(enterDTO.getBuyerNo()==0) {
                dao.insertChat(conn,productNo,sessionID);
                enterDTO= dao.findChat(conn,productNo,sessionID);
            } else if(enterDTO.getBuyerID().equals(sessionID))
                enterDTO.setBuyer(true);
            else
                enterDTO.setBuyer(false);
        }catch (SQLException | NamingException e){
            System.out.println(e.getMessage());
        }finally {
            if(conn!=null)try{conn.close();}catch (Exception e){}
        }
        return enterDTO;
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
}
