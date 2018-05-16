package com.kaisheng.service;

import com.kaisheng.time.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private SendMail sendMail;

    public void reg(){
        System.out.println("查询账号是否注册");
        System.out.println("发送邮件");
        sendMail.sendmail("tom");
        System.out.println("发送结束");
    }
}
