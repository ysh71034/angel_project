package com.angel.UserController;

import com.angel.comm.Action;
import com.angel.comm.Forward;
import com.angel.dto.UserDTO;
import com.angel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JoinResultAction implements Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

        UserService service = UserService.getInstance();
        int result = 0;
        result = service.joinUser(dto);

        Forward forward = new Forward();
        forward.setForward(false);
        if(result>0){
            System.out.println("회원가입 성공");
            forward.setUrl("login.do");
        }else{
            System.out.println("회원가입 실패");
            forward.setUrl("join.do");
        }
        return forward;
    }
}
