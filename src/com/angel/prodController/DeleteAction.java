package com.angel.prodController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.dto.ImageDTO;
import com.angel.dto.ProdDTO;
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

        ProdService service = ProdService.getService();

        ImageDTO dto2 = service.imgPath(productNo);

        System.out.println(productNo);
        System.out.println(realpath);
        String img = dto2.getImagepath();
        System.out.println(img);
        if(img==null)
            img="";
        service.delProd(productNo,realpath+"/"+img);
        Forward forward = new Forward();
        forward.setForward(false);
        forward.setUrl("main.do");
        return forward;
    }
}
