package com.angel.UserController;

import com.angel.dto.InfoDTO;
import com.angel.dto.OrderDTO;
import com.angel.dto.ProdDTO;
import com.angel.dto.RoomDTO;
import com.angel.service.ChatService;
import com.angel.service.ProdService;
import com.angel.service.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
        int myno = Integer.parseInt(req.getParameter("uno"));
        String find = req.getParameter("find");
        ProdService prodService = ProdService.getService();
        resp.setContentType("text/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        if("info".equals(find)){
            UserService userService = UserService.getInstance();
            InfoDTO info= userService.findMyInfo(myno);
            JSONObject o = new JSONObject();
            o.put("userName",info.getUserName());
            o.put("sellCount",info.getSellCount());
            o.put("hotCtg",info.getCategoryName());
            out.print(o);
        } else if("chat".equals(find)){
            // 1. 구매 대화중인 채팅방 목록
            ChatService chatService = ChatService.getChatService();
            List<RoomDTO> roomlist = chatService.findBuyerRoomList(myno);
            JSONArray roomlist_json = new JSONArray();
            for(RoomDTO r:roomlist){
                JSONObject o = new JSONObject();
                o.put("roomNo",r.getRoomNo());
                o.put("productName",r.getProductName());
                roomlist_json.add(o);
            }
            out.print(roomlist_json);
        } else if("order".equals(find)){
            // 2. 나의 구매 내역
            List<OrderDTO> orderlist = prodService.detailOrder(myno);
            JSONArray orderlist_json = new JSONArray();
            for(OrderDTO or:orderlist){
                JSONObject o = new JSONObject();
                o.put("orderNo",or.getOrderNo());
                o.put("productName",or.getProductName());
                o.put("sellerName",or.getSellerName());
                o.put("orderDate",or.getOrderDate());
                orderlist_json.add(o);
            }
            out.print(orderlist_json);
        } else {
            // 3. 나의 판매 상품
            List<ProdDTO> prodlist = prodService.sellerProd(myno);
            JSONArray prodlist_json = new JSONArray();
            for(ProdDTO p:prodlist){
                JSONObject o = new JSONObject();
                o.put("imgpath",p.getDto2().getImagepath());
                o.put("pname",p.getProductName());
                o.put("price",p.getPrice());
                prodlist_json.add(o);
            }
            out.print(prodlist_json);
        }

    }
}
