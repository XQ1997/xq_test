package com.kaisheng.time;

import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;

import static org.junit.Assert.*;

/**
 * 使用jdk自带的时间调度
 */
public class MyTimerTest {

    @Test
    public void run() throws IOException {
        Timer timer = new Timer();
        MyTimer myTimer = new MyTimer();
        //将任务延迟3秒执行
        //timer.schedule(myTimer,3000);
        //在指定的时间去执行任务
        //timer.schedule(myTimer,new Date(System.currentTimeMillis() + 3000));
        //延迟0毫秒，并每3秒执行一次
        //timer.schedule(myTimer, 0,3000);
        //从指定时间开始执行，每秒执行一次
        //timer.schedule(myTimer,new Date(System.currentTimeMillis() + 3000),1000);
        //0延迟，在任务开始100毫秒之后再次执行
        timer.scheduleAtFixedRate(myTimer,0,100);
        System.in.read();
    }
}