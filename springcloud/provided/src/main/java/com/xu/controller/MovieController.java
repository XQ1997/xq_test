package com.xu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    @GetMapping("/movie/{id}")
    public String findByid(@PathVariable Integer id){
        return "movie server provided" + id;
    }

}
