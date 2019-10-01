package com.ywf.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ClassName FirstListener
 * 功能: 整合Listener 方式2
 * Author yangweifeng
 * Date 2019-10-01 12:48
 * Version 1.0
 **/
// @WebListener 如果加上 会进行两次初始化
public class SecondListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed----------SecondListener");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized--------SecondListener");
    }
}
