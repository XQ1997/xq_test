package com.kaisheng.time;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 定义工作
 */
public class Quartz implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //接收从调度者传过来的值
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        System.out.println("hello,quartz" + jobDataMap.get("phone"));
    }
}
