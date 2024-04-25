package com.angel.UserController;

import com.angel.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="ValidationCheck", value="/dupidcheck")
public class ValidAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doReq(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doReq(req, resp);
    }

    private void doReq(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String uid = req.getParameter("checkID");

        UserService service = UserService.getInstance();
        boolean checkID = service.findUser(uid);

        resp.setContentType("text/json; charset=utf-8");
        PrintWriter out = resp.getWriter();

        String dup_msg = "중복된 ID입니다.";
        String available_msg = "사용가능한 ID입니다.";
        String input_msg = "ID를 입력해주세요.";

        if(!"".equals(uid)){
            if(checkID == true){
                //중복
                out.print(dup_msg);
            }else{
                //사용가능
                out.print(available_msg);
            }
        }else{
            //입력X
            out.print(input_msg);
        }

    }
}