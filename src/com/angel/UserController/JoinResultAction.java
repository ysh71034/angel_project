package com.angel.UserController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.dto.UserDTO;
import com.angel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JoinResultAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        String userID = request.getParameter("userID");
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        String address = request.getParameter("address");

        UserDTO dto = new UserDTO();
        dto.setUserID(userID);
        dto.setPassword(password);
        dto.setUserName(userName);
        dto.setAddress(address);

        UserService service = UserService.getInstance();
        service.joinUser(dto);

        Forward forward = new Forward();
        forward.setForward(false);
        forward.setUrl("login.do");
        return forward;
    }
}
