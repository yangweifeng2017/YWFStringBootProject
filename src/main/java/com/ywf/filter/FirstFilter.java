package com.ywf.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * ClassName FirstFilter
 * 功能: 整合FirstFilter方式1
 * Author yangweifeng
 * Date 2019-09-30 21:38
 * Version 1.0
 **/
//@WebFilter(filterName = "FirstFilter",urlPatterns = {"/firstfilter","*.do","*.jsp"})
@WebFilter(filterName = "FirstFilter",urlPatterns = "/firstservlet")
public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入Filter");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("离开Filter");
    }

    @Override
    public void destroy() {

    }
}
