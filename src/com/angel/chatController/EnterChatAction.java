package com.angel.chatController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.dto.ChatEnterDTO;
import com.angel.service.ChatService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EnterChatAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 구매자가 상품 상세정보 페이지 에서 채팅하기를 누를 경우, 판매자가 채팅방 목록에서 해당 채팅방 입장을 누를 경우 생기는 일
        int productNo = Integer.parseInt(request.getParameter("productNo"));
        int buyerNo = Integer.parseInt(request.getParameter("buyerNo")); // 구매자인 경우 0, 판매자인 경우 !0
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("sessionID");
        ChatService service = ChatService.getChatService();
        // 현재 이 서블릿 접근자가 구매자인지 판매자인지 확인한다.
        boolean isSeller = service.isSeller(sessionId);
        Forward forward = new Forward();
        forward.setForward(true);
        if(!isSeller){
            // 서블릿 접근자가 구매자인 경우 자신의 buyerNo를 받아와서 해당 채팅방에 입장한다.
            int getBuyerNo = service.findChat(productNo, sessionId);
            forward.setUrl("/WEB-INF/main.jsp?page=chat/chatroom.jsp?productNo="+productNo+"&buyerNo="+getBuyerNo);
        } else {
            // 서블릿 접근자가 판매자인 경우 바로 해당 채팅방에 입장한다.
            forward.setUrl("/WEB-INF/main.jsp?page=chat/chatroom.jsp?productNo="+productNo+"&buyerNo="+buyerNo);
        }
        request.setAttribute("sessionID",sessionId);
        request.setAttribute("title","천사몰 - 채팅방");
        return forward;
    }
}
