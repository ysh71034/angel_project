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
        // 상품 상세정보 페이지 에서 구매자가 채팅하기를 누를 경우 일어나는 일
        // 판매자는 그 버튼을 누를 수 없다. 판매자의 채팅방이 여러개이기 때문에 파라미터를 뭘 붙여야 할지 정할 수 없음.
        int productNo = Integer.parseInt(request.getParameter("productNo"));
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("sessionID");
        ChatService service = ChatService.getChatService();
        boolean isSeller = service.isSeller(sessionId);
        Forward forward = new Forward();
        forward.setForward(true);
        if(!isSeller){
            ChatEnterDTO enterDTO = service.findChat(productNo, sessionId);
            forward.setUrl("/WEB-INF/main.jsp?page=chat/chatroom.jsp?productNo="+productNo+"&buyerNo="+enterDTO.getBuyerNo());
        } else {
            // 해당 상품 채팅목록 페이지로 이동해야 함.
        }
        request.setAttribute("sessionID",sessionId);
        request.setAttribute("title","천사몰 - 채팅방");
        return forward;
    }
}
