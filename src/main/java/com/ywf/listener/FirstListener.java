package com.ywf.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * ClassName FirstListener
 * 功能: 整合Listener 方式1
 * 根据需要实现不同的Listener接口
 * Author yangweifeng
 * Date 2019-10-01 12:48
 * Version 1.0
 **/
@WebListener
public class FirstListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed----------FirstListener");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized--------FirstListener");
    }
}
