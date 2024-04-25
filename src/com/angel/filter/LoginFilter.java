package com.angel.filter;

import com.angel.comm.Forward;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter(urlPatterns = {"*.do","*.ch"}, initParams = {@WebInitParam(
        name="exclude", value ="/login.do, /login_result.do, /main.do, /join.do, /join_result.do, /logout.do, /validate.do, /category_detail.do"
)})
public class LoginFilter implements Filter {

    private final Set<String> exclude_routerSet = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String exclude_router = filterConfig.getInitParameter("exclude");
        String[] data = exclude_router.split(",");
        for (String item : data) {
            exclude_routerSet.add(item.trim());
        }
    }

    boolean isExclude(HttpServletRequest request) {
        String path = request.getServletPath();
        return exclude_routerSet.contains(path);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);

        if (isExclude(request) || session == null) {
            filterChain.doFilter(request, response);
        } else {
            if (session != null) {
                String sessionID = (String) session.getAttribute("sessionID");
                if (sessionID != null) {
                    System.out.println("filter login");
                    filterChain.doFilter(request, response);
                }else{
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/main.jsp?page=user/login.jsp");
                    dispatcher.forward(request, response);
                }
            }else{
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/main.jsp?page=user/login.jsp");
                dispatcher.forward(request, response);
            }            
        }
    }
}


