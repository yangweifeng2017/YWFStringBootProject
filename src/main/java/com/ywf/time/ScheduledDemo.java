package com.ywf.time;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * ClassName ScheduledDemo
 * 功能: Scheduled定时任务
 * Author yangweifeng
 * Date 2019-10-08 17:33
 * Version 1.0
 **/
@Component
public class ScheduledDemo {
    /**
     * 定时任务方法
     * @Scheduled:设置定时任务
     * cron属性：cron表达式。定时任务触发是时间的一个字符串表达形式
     */
    @Scheduled(cron="10 10 * * * ?")
    public void scheduledMethod(){
        System.out.println("定时器被触发" + new Date());
    }
}
