package com.angel.chatController;

import com.angel.comm.Action;
import com.angel.comm.Forward;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EnterChatAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title","천사몰 - 채팅방");
        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/main.jsp?page=chat/chatroom.jsp");
        return forward;
    }
}
