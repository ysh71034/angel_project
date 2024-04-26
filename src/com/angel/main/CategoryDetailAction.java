package com.angel.main;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.dto.ImageDTO;
import com.angel.dto.ProdDTO;
import com.angel.service.ProdService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryDetailAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int catNo = Integer.parseInt(request.getParameter("catNo"));

        ProdService service = ProdService.getService();
        List<ProdDTO> list = service.prodList(catNo);
        request.setAttribute("list", list);

        Forward forward = new Forward();
        forward.setForward(true);
        forward.setUrl("/WEB-INF/main.jsp?page=category/categoryDetail.jsp");
        return forward;
    }
}
