package com.angel.UserController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DelUserAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("sessionID");
        UserService service = UserService.getInstance();
        int uno = service.findMyNo(sessionId);
        service.deleteUser(uno);

        System.out.println("delete action "+uno);
        Forward forward = new Forward();
        forward.setForward(false);
        forward.setUrl("logout.do");
        return forward;
    }
}
