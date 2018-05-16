package com.kaisheng.time;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SendMail {

    @Async
    public void sendmail(String email){
        System.out.println("Begin send" + email);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(1 == 1){
            throw new RuntimeException("异常" + email);
        }
        System.out.println("end send" + email);
    }
}
