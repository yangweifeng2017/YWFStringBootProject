package com.ywf.config;

import com.ywf.time.QuartzJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * ClassName QuartzConfig
 * 功能: Quartz 配置类
 * 运行方式与参数: TODO
 * Author yangweifeng
 * Date 2019-10-08 18:13
 * Version 1.0
 **/
@Configuration
public class QuartzConfig {
    /**
     * 1. 创建job对象
     */
      @Bean
      public JobDetailFactoryBean getJobDetailFactoryBean(){
          JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
          // 关联job类
          factoryBean.setJobClass(QuartzJob.class);
          return factoryBean;
      }

    /**
     * 2. 创建trigger对象
     */
    @Bean
    public SimpleTriggerFactoryBean getSimpleTriggerFactoryBean(){
        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
        simpleTriggerFactoryBean.setJobDetail(getJobDetailFactoryBean().getObject());
        // 执行间隔的毫秒数 每2000ms执行一次
        simpleTriggerFactoryBean.setRepeatInterval(2000);
        // 总共执行次数
        simpleTriggerFactoryBean.setRepeatCount(5);
        return simpleTriggerFactoryBean;
    }

    /**
     * 2. 创建Cron trigger对象
     */
    @Bean
    public CronTriggerFactoryBean getCronTriggerFactoryBean(){
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(getJobDetailFactoryBean().getObject());
        cronTriggerFactoryBean.setCronExpression("0/2 * * * * ?");
        return cronTriggerFactoryBean;
    }

    /**
     * 3. 创建scheduler对象
     */
    @Bean
     public SchedulerFactoryBean getSchedulerFactoryBean(CronTriggerFactoryBean simpleTriggerFactoryBean,YWFAdaptableJobFactory ywfAdaptableJobFactory){
         SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
         // 关联Trigger
         schedulerFactoryBean.setTriggers(simpleTriggerFactoryBean.getObject());
         // 设置自定义工厂
         schedulerFactoryBean.setJobFactory(ywfAdaptableJobFactory);
         return schedulerFactoryBean;
     }
}
