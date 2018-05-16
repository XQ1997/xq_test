package com.kaisheng.time;

import org.quartz.*;
import org.springframework.context.ApplicationContext;

public class CheckJob implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取spring容器
        try {
            ApplicationContext applicationContext = (ApplicationContext) jobExecutionContext.getScheduler().getContext().get("applicationContext");
            System.out.println("application" + applicationContext);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String value = (String) jobDataMap.get("ticketState");
        System.out.println("检查年票。。。。" + value);
    }
}
