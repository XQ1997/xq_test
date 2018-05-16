package com.kaisheng.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.JedisCluster;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis-cluster.xml")
public class SpringJedisClusterTest {

    @Autowired
    private JedisCluster jedisCluster;

    @Test
    public void get(){
        jedisCluster.set("name:1001","tom");

        String name = jedisCluster.get("name:1001");
        System.out.println(name);
    }
}
