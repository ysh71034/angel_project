package com.angel.chatController;

import com.angel.dto.ChatDTO;
import com.angel.dto.RoomDTO;
import com.angel.service.ChatService;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint("/chat.ch")
public class ChatServer {
    private static Map<Session,String> sessions = Collections.synchronizedMap(new HashMap<>());

    private RoomDTO roomInfo;
    private ChatDTO message = new ChatDTO();
    @OnOpen
    public void onOpen(Session session) {
        sessions.put(session,session.getId());
        for(Session s:sessions.keySet()){
            System.out.println("mapinit: "+sessions.get(s));
        }
    }

    private String[] metaRegister(ChatService service, String msg, Session session){
        String user = msg.split("&")[1];
        String rno = user.split(":")[1];
        sessions.put(session, user);
        roomInfo = service.findRoomInfo(Integer.parseInt(rno));
        String[] socketId = {user.split(":")[0],rno};
        message.setProductNo(roomInfo.getProductNo());
        message.setBuyerNo(roomInfo.getBuyerNo());
        message.setWriter(socketId[0]);
        System.out.println("metaset: "+ sessions.get(session));
        System.out.println("rinfoset: "+roomInfo.getRoomNo()+roomInfo.getProductNo()+roomInfo.getBuyerNo());
        System.out.println("msgset: "+message.getProductNo()+message.getBuyerNo()+message.getWriter());
        return socketId;
    }

    @OnMessage
    public void onMessage(String msg, Session session) {
        ChatService service = ChatService.getChatService();
        if(msg.contains("init_conn")){
            System.out.println(msg);
            String[] socketId = metaRegister(service,msg,session);
            for (Session s : sessions.keySet()) {
                if(socketId[1].equals(sessions.get(s).split(":")[1])){
                    try{
                        s.getBasicRemote().sendText(socketId[0]+" 님이 입장하셨습니다.&enter");
                    }catch (IOException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        } else {
            message.setContent(msg);
            service.insertChat(message);
            for (Session s : sessions.keySet()) {
                if(sessions.get(session).split(":")[1].equals(
                        sessions.get(s).split(":")[1])){
                    try{
                        s.getBasicRemote().sendText(msg+"&"+sessions.get(session));
                    }catch (IOException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("in ChatServer socket closed: " + session.getId());
        if(sessions.size()!=0){
            for (Session s : sessions.keySet()) {
                if(!s.getId().equals(session.getId())
                        && sessions.get(session).split(":")[1].equals(
                        sessions.get(s).split(":")[1])){
                    try{
                        s.getBasicRemote().sendText(sessions.get(session).split(":")[0]+" 님이 퇴장하셨습니다.&enter");
                    }catch (IOException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
            sessions.remove(session);
        }

    }

    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }
}
