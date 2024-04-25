package com.angel.chatController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.service.ChatService;
import com.angel.service.ProdService;
import com.angel.service.UserService;
import com.sun.jna.platform.win32.Netapi32Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ContractAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 판매자가 채팅방에서 거래확정 버튼을 누르면 오는 곳
        int pno = Integer.parseInt(request.getParameter("productNo"));
        int bno = Integer.parseInt(request.getParameter("buyerNo"));
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("sessionID");
        // 1. 주문 테이블에 상품 등록
        ProdService prodService = ProdService.getService();
        prodService.contract(pno,bno);
        // 2. 판매자의 sellcount +1
        UserService userService = UserService.getInstance();
        userService.addSellCount(sessionId);
        // 3. 해당 상품 채팅 메시지 전체 삭제
        ChatService chatService = ChatService.getChatService();
        chatService.deleteChat(pno);
        Forward forward = new Forward();
        forward.setForward(false);
        forward.setUrl("mypage.do");
        return forward;
    }
}
