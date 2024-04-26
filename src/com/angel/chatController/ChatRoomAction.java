package com.angel.chatController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.dto.ProdDTO;
import com.angel.dto.RoomDTO;
import com.angel.service.ChatService;
import com.angel.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ChatRoomAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 상품 상세정보 페이지에서 판매자가 채팅방 목록을 누르면 일어나는 일
        // 마이페이지에서 판매자가 채팅방 목록을 누르면 일어나는 일
        int pno = Integer.parseInt(request.getParameter("productNo"));
        HttpSession session = request.getSession();
        // 접근자가 해당상품의 판매자인지 확인
        String sessionId = (String) session.getAttribute("sessionID");
        ChatService service = ChatService.getChatService();
        boolean isSeller = service.isSeller(pno,sessionId);
        Forward forward = new Forward();
        if(isSeller){
            // 판매 대화중인 채팅방 리스트 정보 받아오기
            List<RoomDTO> list = service.findSellerRoomList(pno);
            // 해당 상품 정보 받아오기
            ProdService prodService = ProdService.getService();
            ProdDTO prodDTO = prodService.detailProd(pno);
            request.setAttribute("list",list);
            request.setAttribute("pImg",prodDTO.getDto2().getImagepath());
            request.setAttribute("pName",prodDTO.getProductName());
            request.setAttribute("title","천사몰 - 상품별 채팅방");
            forward.setForward(true);
            forward.setUrl("/WEB-INF/main.jsp?page=chat/chatroomlist.jsp?productNo="+pno);
        } else {
            // 접근자가 해당 상품의 판매자가 아닌경우 이 페이지에는 접근불가
            forward.setForward(false);
            forward.setUrl("main.do");
        }
        return forward;
    }
}
