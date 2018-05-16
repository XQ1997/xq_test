package com.kaisheng.time;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;

import static org.junit.Assert.*;

public class SimpleQuartzTest {

    @Test
    public void quartz() throws SchedulerException, IOException {
        //1.定义任务
        JobDetail jobDetail = JobBuilder.newJob(Quartz.class).build();
        //2.定义触发的形式
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule();
        //间隔10秒
        simpleScheduleBuilder.withIntervalInMilliseconds(1000);
        //永远执行下去
        simpleScheduleBuilder.repeatForever();

        //3.通过触发形式创建触发器
        Trigger trigger = TriggerBuilder.newTrigger().withSchedule(simpleScheduleBuilder).build();
        //4.创建任务调度者对象
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        //5.调度任务
        scheduler.scheduleJob(jobDetail,trigger);
        scheduler.start();

        System.in.read();
    }
}