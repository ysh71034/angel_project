package com.angel.chatController;

import com.angel.dto.ChatDTO;
import com.angel.dto.ChatListDTO;
import com.angel.service.ChatService;
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

@WebServlet(urlPatterns = "/chatlist")
public class ChatListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doReq(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doReq(req,resp);
    }

    private void doReq(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bno = Integer.parseInt(req.getParameter("bno"));
        int pno = Integer.parseInt(req.getParameter("pno"));
        ChatService service = ChatService.getChatService();
        List<ChatDTO> chatslist = service.findChatList(bno,pno);
        JSONArray chatlist_json = new JSONArray();
        for(ChatDTO dto:chatslist){
            JSONObject o = new JSONObject();
            o.put("writer",dto.getWriter());
            o.put("content",dto.getContent());
            chatlist_json.add(o);
        }
        resp.setContentType("text/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(chatlist_json);
    }
}
