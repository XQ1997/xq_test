package com.kaisheng.redis;

import com.google.gson.Gson;
import com.kaisheng.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis.xml")
public class SpringRedisTest {

    @Autowired
    private JedisPool jedisPool;

    Jedis jedis = null;

    @Before
    public void init(){
        jedis = jedisPool.getResource();
    }

    @Test
    public void StringOperation(){
        jedis.set("address","北京");

        String address = jedis.get("address");
        System.out.println(address);
    }

    @Test
    public void userss(){
        User user = new User(1,"tom","北京",23);
        String json = new Gson().toJson(user);
        jedis.set("user:1",json);

        String u = jedis.get("user:1");
        User use = new Gson().fromJson(u,User.class);
        System.out.println(use);
    }

    @After
    public void destory(){
        jedis.close();
    }

}
