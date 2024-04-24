package com.angel.chatController;

import com.angel.dto.ChatDTO;
import com.angel.service.ChatService;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ServerEndpoint("/chat")
public class ChatServer {
    private static final Map<Session,String> sessions = Collections.synchronizedMap(new HashMap<>());
    private static final String[] params = new String[2];

    @OnOpen
    public void onOpen(Session session) {
        sessions.put(session,session.getId());
        System.out.println("map_size: "+sessions.size());
    }

    @OnMessage
    public void onMessage(String msg, Session session) throws IOException {
        ChatService service = ChatService.getChatService();
        ChatDTO dto = new ChatDTO();
        if(msg.contains("init_conn")){
            if(params[0]==null) {
                params[0] = msg.split(":")[2];
                params[1] = msg.split(":")[3];
            }
            String user = msg.split(":")[1];
            if(service.isSeller(user))
                sessions.put(session, user);
            else
                sessions.put(session, user);
            for (Session s : sessions.keySet()) {
                s.getBasicRemote().sendText(user+" 님이 입장하셨습니다.&enter");
            }
        } else {
            dto.setProductNo(Integer.parseInt(params[0]));
            dto.setBuyerNo(Integer.parseInt(params[1]));
            dto.setContent(msg);
            dto.setWriter(sessions.get(session));
            service.insertChat(dto);
            for (Session s : sessions.keySet()) {
                s.getBasicRemote().sendText(msg+"&"+sessions.get(session));
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Connection closed: " + session.getId());
    }

    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }
}