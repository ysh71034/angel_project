package com.angel.prodController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.dto.ProdDTO;
import com.angel.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListProdAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("main.do");
        return forward;
    }
}
