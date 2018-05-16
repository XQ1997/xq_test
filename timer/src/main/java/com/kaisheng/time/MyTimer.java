package com.kaisheng.time;

import java.util.TimerTask;

/**
 * 使用jdk自带的时间调度，继承该抽象类
 */
public class MyTimer extends TimerTask {

    @Override
    public void run() {
        System.out.println("start....");
        System.out.println("jdk 自带的TimerTask");
        System.out.println("end....");
    }
}
