package com.angel.UserController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet(urlPatterns = "/findlist.my")
public class MyListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doReq(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doReq(req,resp);
    }

    private void doReq(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // mypage 에 find 해야 되는 리스트
        // 1. 대화중인 채팅방 목록
        // 2. 나의 거래 내역
        // 3. 나의 판매 상품
        // 4. 내가 찜한 상품
    }
}
