package com.kaisheng.time;

public class SpringTimer implements Runnable {

    public void run() {
        System.out.println("hello spring timer" + Thread.currentThread().getName());
    }
}
