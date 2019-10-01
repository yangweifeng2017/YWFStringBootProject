package com.ywf.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName FirstServlet
 * 功能: 整合Servlet方式 1
 * Author yangweifeng
 * Date 2019-09-30 20:58
 * Version 1.0
 **/
@WebServlet(name = "FirstServlet",urlPatterns = "/firstservlet")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FirstServlet");
    }
}