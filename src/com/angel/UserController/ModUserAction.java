package com.angel.UserController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.dto.UserDTO;
import com.angel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ModUserAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("sessionID");
        UserService service = UserService.getInstance();
        int uno = service.findMyNo(sessionId);
        System.out.println("mod user action "+uno);

        UserDTO dto = new UserDTO();
        dto = service.userInfo(uno);

        request.setAttribute("dto", dto);

        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/main.jsp?page=user/modify.jsp&uno="+uno);
        return forward;
    }
}
