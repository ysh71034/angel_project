package com.angel.prodController;

import com.angel.comm.Action;
import com.angel.comm.Forward;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegProdAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/main.jsp?page=prod/regprod.jsp");
        return forward;
    }
}
