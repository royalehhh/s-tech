package com.stk.website.filter;

import com.stk.website.comm.ErrorConstant;
import com.stk.website.comm.Global;
import com.stk.website.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
@Slf4j
public class PermissionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        if (url.contains("manage")){
            String token = request.getParameter("token");
            if (token == null || !Global.tokenMap.containsValue(token)){
                response.setStatus(ErrorConstant.NO_PERMISSION_CODE);
                response.sendRedirect("/permission");
                return;
            }
            request.getSession().setMaxInactiveInterval(60*60);
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
