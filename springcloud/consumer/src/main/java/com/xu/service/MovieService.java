package com.xu.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MOVIE-PROVIDER")
public interface MovieService {
    /**
     * 这里的id不是通过url中获取到的，而是
     * @param id
     * @return
     */
    @GetMapping("/movie/{id:\\d+}")
    String fingByid(@PathVariable(name = "id") Integer id);
}
