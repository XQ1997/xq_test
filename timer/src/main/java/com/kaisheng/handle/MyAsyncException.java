package com.kaisheng.handle;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MyAsyncException extends SimpleAsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        System.out.println("...............");
        System.out.println("Exception " + throwable.getMessage());
        System.out.println("Method name" + method.getName());
        for(Object param : objects){
            System.out.println("Parameter value" + param);
        }
    }
}
