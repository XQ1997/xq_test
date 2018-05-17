package com.kaisheng.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/movie")
//解决跨域之间的请求，前端默认端口为8080
//这里指定当前的HomeController中所有的方法可以处理http://localhost:8080域上的请求，*代表所有  maxAge属性代表生存周期
@CrossOrigin(origins = "http://localhost:8080")
public class HomeController {

    @GetMapping("/{id}")
    public Map<String,String> findByid(@PathVariable Integer id){
        Map<String,String> map = new HashMap<>();
        map.put("name","啊哈哈哈");
        map.put("rate","9.5");

        return map;
    }

}
