package com.ywf;

import com.ywf.filter.SecondFilter;
import com.ywf.listener.SecondListener;
import com.ywf.servlet.SecondServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * ClassName App
 * 功能: 启动类
 * Author yangweifeng
 * Date 2019-09-30 20:36
 * Version 1.0
 **/
@SpringBootApplication
@ServletComponentScan //在SpringBoot启动时 扫描@WebServlet注解，并将该类实例化
@MapperScan("com.ywf.mapper")           // mybaties Mapper接口扫描
@EnableScheduling     // 定时器注解
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ServletRegistrationBean getServletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new SecondServlet());
        servletRegistrationBean.addUrlMappings("/secondservlet");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean getFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new SecondFilter());
        filterRegistrationBean.addUrlPatterns(new String[]{"/secondservlet"}); //传递字符串数组
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean<SecondListener> getServletListenerRegistrationBean() {
        ServletListenerRegistrationBean<SecondListener> bean = new ServletListenerRegistrationBean<>(new SecondListener());
        return bean;
    }

}