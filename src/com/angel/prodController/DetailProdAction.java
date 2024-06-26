package com.angel.prodController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.dto.ProdDTO;
import com.angel.service.ChatService;
import com.angel.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class DetailProdAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //제품 상세 조회
        int productNo = Integer.parseInt(request.getParameter("productNo"));
        ProdService service = ProdService.getService();
        ProdDTO dto =service.detailProd(productNo);
        request.setAttribute("dto",dto);
        System.out.println("상세"+productNo);
        //판매자의 다른 상품 확인
        int sellerNo = dto.getSellerNo();
        System.out.println("sellerNo"+sellerNo);
        List<ProdDTO> sellerprod = service.sellerProd(sellerNo,"detail");
        request.setAttribute("sellerprod",sellerprod);
        //같은 카테고리의 다른 상품
        int categoryNo = dto.getCategoryNo();
        System.out.println("categoryNo"+categoryNo);
        List<ProdDTO> catprod = service.catProd(categoryNo);
        request.setAttribute("catprod",catprod);
        // 이 페이지 접속자가 판매자인지 구매자인지 결정
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("sessionID");
        ChatService chatService = ChatService.getChatService();
        boolean isSeller = chatService.isSeller(productNo,sessionId);
        if(isSeller)
            request.setAttribute("roll","seller");
        else
            request.setAttribute("roll","buyer");
        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/main.jsp?page=prod/detailprod.jsp");

        return forward;
    }
}
