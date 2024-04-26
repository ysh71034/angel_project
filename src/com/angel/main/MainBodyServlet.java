package com.angel.main;

import com.angel.comm.Forward;
import com.angel.dto.ProdDTO;
import com.angel.service.ProdService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/brandnew.bn")
public class MainBodyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doReq(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doReq(req, resp);
    }
    private void doReq(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProdService service = ProdService.getService();
        List<ProdDTO> list = service.brandNewList();
        JSONArray brandNewList = new JSONArray();
        for(ProdDTO dto : list){
            JSONObject o = new JSONObject();
            o.put("productNo", dto.getProductNo());
            o.put("imagePath", dto.dto2.getImagepath());
            brandNewList.add(o);
        }
        resp.setContentType("text/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(brandNewList);
    }
}