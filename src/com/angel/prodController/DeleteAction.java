package com.angel.prodController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.dto.ImageDTO;
import com.angel.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productNo = Integer.parseInt(request.getParameter("productNo"));
        String realpath = request.getServletContext().getRealPath("upload");
        String img = request.getParameter("img");

        ProdService service = ProdService.getService();
        service.delProd(productNo,img);
        ImageDTO dto = service.imgPath(productNo);

        System.out.println(productNo);
        System.out.println(realpath);
        System.out.println(img);
        System.out.println(dto.getImagepath());


        Forward forward = new Forward();
        forward.setForward(false);
        forward.setUrl("main.do");
        return forward;
    }
}
