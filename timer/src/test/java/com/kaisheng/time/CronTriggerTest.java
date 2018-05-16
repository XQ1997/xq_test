package com.kaisheng.time;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

public class CronTriggerTest {

    @Test
    public void test() throws SchedulerException, IOException {
        //定义任务
        //传值，传到任务中，定义map集合
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("phone","123456");
        JobDetail detail = JobBuilder.newJob(Quartz.class).setJobData(jobDataMap).build();
        //定义触发的形式
        ScheduleBuilder builder = CronScheduleBuilder.cronSchedule("0/1 * * * * ? *");
        //3.通过触发形式创建触发器
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(builder).build();
        //4.创建任务调度者对象
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        //5.调度任务
        scheduler.scheduleJob(detail,trigger);
        scheduler.start();

        System.in.read();
    }
}
