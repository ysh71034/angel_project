//package com.angel.filter;
//
//import com.angel.comm.Forward;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebInitParam;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Set;
//
//@WebFilter(urlPatterns = "*.do", initParams = {@WebInitParam(
//        name="exclude"
//)})
//public class LoginFilter implements Filter {
//
//    private final Set<String> exclude =
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        HttpSession session = request.getSession(false);
//
//        if(exclude.contains(request.getServletPath()) || session == null){
//            filterChain.doFilter(request, response);
//        }else {
//
//            String userID = (String)session.getAttribute("userID");
//
//            if(userID != null){
//                System.out.println("filter login");
//                filterChain.doFilter(request, response);
//            }
//            Forward forward = new Forward();
//            forward.setForward(true);
//            forward.setUrl("/WEB-INF/login/login.jsp");
//
//        }
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
