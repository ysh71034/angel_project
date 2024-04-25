package com.angel.prodController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.dto.ImageDTO;
import com.angel.dto.ProdDTO;
import com.angel.dto.UserDTO;
import com.angel.service.ProdService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import org.checkerframework.checker.units.qual.A;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Array;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Enumeration;
import java.util.List;

public class RegProdResultAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String sessionID = (String)session.getAttribute("sessionID");
        int filesize = 1024*1024*100;
        String uploadpath = request.getServletContext().getRealPath("upload");
        MultipartRequest multi = new MultipartRequest(request,uploadpath,filesize,"utf-8",new DefaultFileRenamePolicy());

        String name = multi.getParameter("productName");
        String category = multi.getParameter("category");
        int price = Integer.parseInt(multi.getParameter("price"));
        String description = multi.getParameter("description");
        Date registerDate = Date.valueOf(LocalDate.now());

        System.out.println(category);

        UserDTO userdto = new UserDTO();


        System.out.println(sessionID);

        List<ImageDTO> imgList = new ArrayList<>();
        Enumeration files = multi.getFileNames();
        List<String> filenames = new ArrayList<>();
        while (files.hasMoreElements()){
            String file = (String) files.nextElement();
            String filename = multi.getFilesystemName(file);

            if(filename!=null){
                ImageDTO dto2 = new ImageDTO();
                dto2.setImagepath(filename);
                imgList.add(dto2);
                filenames.add(filename);
            }
        }
        for(String filename:filenames){
            System.out.println(filename);
        }

        ProdService service = ProdService.getService();
        int sellerNo = service.findSellerNo(sessionID);
        System.out.println(sellerNo);


        ProdDTO dto = new ProdDTO();

        dto.setSellerNo(sellerNo);
        dto.setProductName(name);
        dto.setCategoryName(category);
        dto.setPrice(price);
        dto.setDescription(description);
        dto.setRegisterDate(registerDate);

        service.insertProd(dto,imgList);

        Forward forward = new Forward();
        forward.setForward(false);

        forward.setUrl("main.do");
        return forward;
    }
}
