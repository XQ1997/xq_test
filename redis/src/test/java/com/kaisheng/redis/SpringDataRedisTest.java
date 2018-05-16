package com.kaisheng.redis;

import com.google.gson.Gson;
import com.kaisheng.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-data-redis.xml")
public class SpringDataRedisTest {

    private RedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
//        this.redisTemplate.setValueSerializer(new StringRedisSerializer());
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
    }

    @Test
    public void getstring() {
        redisTemplate.opsForValue().set("hello2", "hello data sda");

        String hello = (String) redisTemplate.opsForValue().get("hello2");
        System.out.println(hello);
    }

    @Test
    public void user(){
        User user = new User(1003,"poal","上海",22);
        //redisTemplate.opsForValue().set("user:1002",new Gson().toJson(user));

      /*  String us = (String) redisTemplate.opsForValue().get("user:1003");
        User use = new Gson().fromJson(us,User.class);
        System.out.println(use);*/
      redisTemplate.opsForValue().set("user:1003",user);

      User user1 = (User) redisTemplate.opsForValue().get("user:1003");
        System.out.println(user1);
    }
}
