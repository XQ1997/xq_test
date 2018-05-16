package com.kaisheng.time;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringAnnonation {

    //@Scheduled(fixedRate = 3000)
    public void ratejob(){
        System.out.println("rateJob");
    }

   // @Scheduled(fixedDelay = 1000)
    public void delay(){
        System.out.println("DelayJob:" + System.currentTimeMillis());
    }

    //@Scheduled(fixedDelay = 2000,initialDelay = 1000)
    public void initDelayJob(){
        System.out.println("initDelayJob:" + System.currentTimeMillis());
    }

    //@Scheduled(cron = "0/1 * * * * *")
    public void cronJob(){
        System.out.println("cron");
    }

}
