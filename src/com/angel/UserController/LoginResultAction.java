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

public class LoginResultAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Login result");

        String userID = request.getParameter("userID");
        String password = request.getParameter("password");

        UserService service = UserService.getInstance();


        UserDTO dto = service.loginUser(userID, password);
        Forward forward = new Forward();
        forward.setForward(false);

        if(userID != null && password != null) {
            if (userID.equals(dto.getUserID()) && password.equals(dto.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("sessionID", userID);

//                session.setMaxInactiveInterval(0);
                System.out.println("login success");
                forward.setUrl("main.do");
            } else {
                System.out.println("login fail");
                forward.setUrl("login.do");
            }
        } else {
          forward.setUrl("login.do");
        }
        return forward;
    }
}
