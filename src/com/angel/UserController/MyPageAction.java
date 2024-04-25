package com.angel.UserController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyPageAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title","천사몰 - 마이페이지");
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("sessionID");
        UserService service = UserService.getInstance();
        int uno = service.findMyNo(sessionId);
        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/main.jsp?page=user/mypage.jsp&userNo="+uno);
        return forward;
    }
}
