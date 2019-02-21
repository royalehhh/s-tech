package com.stk.website.filter;

import com.stk.website.comm.Constant;
import com.stk.website.comm.ErrorConstant;
import com.stk.website.comm.Global;
import com.stk.website.exception.ServiceException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

public class PermissionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURI();
        if (url.contains("login")){
            String name = request.getParameter("name");
            String token = UUID.randomUUID().toString().replace("-","");
            Global.tokenMap.put(name, token);
        }
        if (url.contains("manage")){
            String token = (String) request.getSession().getAttribute("token");
            String userName = (String) request.getSession().getAttribute("name");
            if (token == null || !token.equals(Global.tokenMap.get(userName))){
                throw new ServiceException(ErrorConstant.NO_PERMISSION_CODE, ErrorConstant.NO_PERMISSION_MSG);
            }
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
