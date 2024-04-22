package com.angel.UserController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.dto.UserDTO;
import com.angel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginResultAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userID = request.getParameter("userID");
        String password = request.getParameter("password");

        UserService service = UserService.getInstance();
        UserDTO dto = new UserDTO();
        dto.setUserID(userID);
        dto.setPassword(password);
        service.loginUser(dto);

        Forward forward = new Forward();
        forward.setForward(false);

        if(userID.equals(dto.getUserID()) && password.equals(dto.getPassword())){
            System.out.println("success");
            forward.setUrl("main.do");
        }else{
            System.out.println("fail");
            forward.setUrl("login.do");
        }
        return forward;
    }
}
