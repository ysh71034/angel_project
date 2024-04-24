package com.angel.UserController;

import com.angel.comm.Action;
import com.angel.comm.Forward;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userID = (String)session.getAttribute("sessionID");

        if(userID != null){
            session.invalidate();
        }

        Forward forward = new Forward();
        forward.setForward(false);
        forward.setUrl("main.do");
        return forward;
    }
}
