package com.angel.UserController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.dto.UserDTO;
import com.angel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ModUserResultAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("sessionID");
        UserService service = UserService.getInstance();
        int uno = service.findMyNo(sessionId);

        String userID = request.getParameter("userID");
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        String postCode = request.getParameter("postcode");
        String roadAddr = request.getParameter("roadAddress");
        String jibunAddr = request.getParameter("jibunAddress");
        String extraAddr = request.getParameter("extraAddress");
        String detailAddr = request.getParameter("detailAddress");

        StringBuilder address = new StringBuilder();
        address.append(postCode);
        address.append(" ");
        address.append(roadAddr);
        address.append(" ");
        address.append(jibunAddr);
        address.append(" ");
        address.append(extraAddr);
        address.append(" ");
        address.append(detailAddr);

        UserDTO dto = new UserDTO();
        dto.setUserID(userID);
        dto.setPassword(password);
        dto.setUserName(userName);
        dto.setAddress(String.valueOf(address));

        service.modUser(dto, uno);
        request.setAttribute("dto", dto);

        Forward forward = new Forward();
        forward.setForward(false);
        forward.setUrl("main.do");
        return forward;
    }
}
