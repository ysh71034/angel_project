package com.angel.UserController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ValidateAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uid = request.getParameter("uid");
        System.out.println(uid);

        UserService service = UserService.getInstance();
        boolean checkID = service.findUser(uid);
        System.out.println(checkID);

        request.setAttribute("checkID", checkID);

        Forward forward = new Forward();
        forward.setForward(false);
        forward.setUrl("join.do");
        return forward;
    }
}
