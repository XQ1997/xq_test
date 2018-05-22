package com.xu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 消费固定ip
     * @param id
     * @return
     */
    @GetMapping("/movie/{id}")
    public String shop(@PathVariable Integer id){
        String url = "http://localhost:8080/movie/" + id;
        return restTemplate.getForObject(url,String.class);
    }
}
