package com.xu.controller;

import com.xu.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private MovieService movieService;

    @GetMapping("/movie/{id:\\d+}")
    public String shop(@PathVariable Integer id){
        return movieService.fingByid(id);
    }


    /**
     * 第二种使用ribbon客户端负载均衡器
     * @param id
     * @return
     */
   /* @GetMapping("/movie/{id:\\d+}")
    public String shop(@PathVariable Integer id){
        String url = "http://MOVIE-PROVIDER/movie/" + id;
        return restTemplate.getForObject(url,String.class);
    }*/


    /**
     * 第一种，使用LoadBalancerClient接口负载均衡的功能获取serviceInstance对象获取服务的名字
     */
  /*  @GetMapping("/movie/{id}")
    public String shop(@PathVariable Integer id){
        //根据服务名称从Eureka上发现服务的提供者，并使用负载均衡的方式返回提供者的地址
        ServiceInstance serviceInstance = loadBalancerClient.choose("MOVIE-PROVIDER");
        //第一种               主机ip+端口    进行硬编码
        //String url = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/movie/"+id;
        //第二种  直接从集群中获取
        String url = serviceInstance.getUri().toString() + "/movie/" + id;
        System.out.println("id:" + id);
        return restTemplate.getForObject(url,String.class);
    }*/

    /**
     * 消费提供者提供的固定ip，与Eureka无关
     * @param id
     * @return
     */
   /* @GetMapping("/movie/{id}")
    public String shop(@PathVariable Integer id){
        String url = "http://localhost:8080/movie/" + id;
        return restTemplate.getForObject(url,String.class);
    }*/


}
