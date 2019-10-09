package com.ywf.exception;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * ClassName GlobalExeption
 * 功能: 公共异常处理类
 * Author yangweifeng
 * Date 2019-10-06 17:45
 * Version 1.0
 **/
@ControllerAdvice
public class GlobalExeption {
     /*
       针对异常进行判断，做不同的逻辑处理，返回不同的值
    */
    /**
     * 所有ArithmeticException异常处理类
     * @param e Exception
     * @return  String
     */
    @ExceptionHandler(value = {java.lang.ArithmeticException.class})
    public String handleArithmeticException(Exception e){
        e.printStackTrace();
        return "ArithmeticException";
    }
    /**
     * 所有ArithmeticException异常处理类
     * @param e Exception
     * @return  String
     */
    @ExceptionHandler(value = {java.lang.NullPointerException.class})
    public String handleNullPointerException(Exception e){
        e.printStackTrace();
        return "NullPointerException";
    }
    /**
     * 处理所有异常(同一个方法)
     * @param e Exception
     * @return  String
     */
    @ExceptionHandler(value = {java.lang.Exception.class})
    public String handleException(Exception e){
        /*
         针对异常进行判断，做不同的逻辑处理，返回不同的值
        */
        e.printStackTrace();
        return "Exception";
    }
}
