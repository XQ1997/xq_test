package com.xu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @GetMapping()
    public String findByid(){
        return "movie server provided";
    }

}
