package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"},initParams = {
        @WebInitParam(name = "exclude",value = "/login.jsp,/loginServlet,/logoutServlet,.css,.jpg,.png,.js")
})
public class LoginFilter implements Filter {

    public static String excludeString;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        excludeString = filterConfig.getInitParameter("exclude");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        Object user = session.getAttribute("user");
        if (isExist(uri) || uri.equals(request.getContextPath()+"/")){
            filterChain.doFilter(request, response);
        }else {
            if (user != null){
                filterChain.doFilter(request, response);
            }else {
                response.sendRedirect("/view/login.jsp");
            }
        }

    }

    public static boolean isExist(String uri){
        String[] arr = excludeString.split(",");
        boolean flag = false;
        for (String s : arr) {
            if (uri.endsWith(s)){
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void destroy() {

    }
}
