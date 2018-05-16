package com.kaisheng.time;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-task.xml")
public class SpringTimerTest {

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Test
    public void test() throws IOException {
         SpringTimer timer = new SpringTimer();

         //立即执行任务
         //taskScheduler.execute(timer);
        //在指定时间执行任务
        //taskScheduler.schedule(timer,new Date(System.currentTimeMillis() + 3000));
        //延迟循环执行任务
        //taskScheduler.scheduleWithFixedDelay(timer,10000);
        //按照指定时间执行任务
        //taskScheduler.scheduleAtFixedRate(timer,new Date(),0);
        //基于cron表达式执行任务
        taskScheduler.schedule(timer,new CronTrigger("0/1 * * * * ?"));

        System.in.read();
    }

}