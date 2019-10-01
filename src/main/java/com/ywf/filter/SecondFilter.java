package com.ywf.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * ClassName FirstFilter
 * 功能: 整合Filter方式2
 * Author yangweifeng
 * Date 2019-09-30 21:38
 * Version 1.0
 **/
public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入SecondFilter");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("离开SecondFilter");
    }

    @Override
    public void destroy() {

    }
}
