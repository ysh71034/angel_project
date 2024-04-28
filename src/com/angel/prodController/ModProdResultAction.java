package com.angel.prodController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.dto.ImageDTO;
import com.angel.dto.ProdDTO;
import com.angel.service.ProdService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
//import org.checkerframework.checker.units.qual.A;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ModProdResultAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int filesize = 1024*1024*1024;
        String uploadpath = request.getServletContext().getRealPath("upload");
        MultipartRequest multi = new MultipartRequest(request,uploadpath,filesize,"utf-8",new DefaultFileRenamePolicy());


        int productNo = Integer.parseInt(multi.getParameter("productNo"));
        System.out.println(productNo);
        String name = multi.getParameter("productName");
        System.out.println(name);

        String category = multi.getParameter("category");
        System.out.println(category);
        int price = Integer.parseInt(multi.getParameter("price"));

        String description = multi.getParameter("description");
        System.out.println(description);

        List<ImageDTO> imgList = new ArrayList<>();
        Enumeration files = multi.getFileNames();
        while (files.hasMoreElements()){
            String file = (String) files.nextElement();
            String filename = multi.getFilesystemName(file);

            if(filename!=null){
                ImageDTO dto2 = new ImageDTO();
                dto2.setImagepath(filename);
                imgList.add(dto2);
            }
        }
        ProdService service = ProdService.getService();
        ProdDTO dto = new ProdDTO();
        dto.setProductNo(productNo);
        dto.setProductName(name);
        dto.setCategoryName(category);
        dto.setPrice(price);
        dto.setDescription(description);

        int result = service.modProd(dto,imgList);
        request.setAttribute("result",result);
        Forward forward = new Forward();
        forward.setForward(false);
        forward.setUrl("main.do");
        return forward;
    }
}
