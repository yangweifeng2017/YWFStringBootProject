package com.ywf.time;

import com.ywf.service.impl.UserServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * ClassName QuartzDemo
 * 功能: TODO
 * 运行方式与参数: TODO
 * Author yangweifeng
 * Date 2019-10-08 18:11
 * Version 1.0
 **/
public class QuartzJob implements Job {
    @Autowired
    private UserServiceImpl userService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            System.out.println(userService.selectUserById(2).toString());
        }catch (Exception e){
          e.printStackTrace();
        }
    }
}
