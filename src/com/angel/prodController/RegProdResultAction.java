package com.angel.prodController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class RegProdResultAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Forward forward = new Forward();
        forward.setForward(false);
        forward.setUrl("main.do");
        return forward  ;
    }
}
