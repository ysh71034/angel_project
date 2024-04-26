package com.angel.UserController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.dto.ProdDTO;
import com.angel.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SellerInfoAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //판매자 정보 조회
        int sellerNo = Integer.parseInt(request.getParameter("sellerNo"));
        ProdService service = ProdService.getService();
        ProdDTO dto = service.sellerInfo(sellerNo);
        request.setAttribute("dto",dto);

        //판매중인 상품
        List<ProdDTO> sellerprod = service.sellerProd(sellerNo);
        request.setAttribute("sellerprod",sellerprod);
        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/main.jsp?page=user/sellerpage.jsp");
        return forward;
    }
}
