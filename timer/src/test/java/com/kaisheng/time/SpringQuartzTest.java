package com.kaisheng.time;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-quartz.xml","classpath:spring-datasource.xml"})
public class SpringQuartzTest {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Test
    public void run() throws IOException {
        System.out.println("hello spring in quartz");
        System.in.read();
    }

    /**
     * 动态添加任务
     */
    @Test
    public void addJob() throws SchedulerException, IOException {
        //1.jobDetail
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("weixin","tom");

        JobDetail jobDetail = JobBuilder.newJob(SendWeixin.class)
                //定义任务的唯一性，依靠名称+组名
                .withIdentity("weixin:tom","weixin")
                .setJobData(dataMap).build();
        String cron = "0/10 * * * * ? *";

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(cronScheduleBuilder).build();

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();

        System.in.read();
    }

    @Test
    public void delete() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        scheduler.deleteJob(new JobKey("weixin:tom","weixin"));
    }
}
