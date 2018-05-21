package com.xu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProvidedApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProvidedApplication.class, args);
    }
}
