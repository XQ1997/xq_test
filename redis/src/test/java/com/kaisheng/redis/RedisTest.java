package com.kaisheng.redis;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedisTest {

    Jedis jedis = null;

    @Before
    public void init(){
        jedis = new Jedis("192.168.248.131",6379);
    }

    @Test
    public void test(){
        String pang = jedis.ping();
        System.out.println(pang);
    }

    @Test
    public void setString(){
        jedis.set("name","tom");

        String name = jedis.get("name");
        Assert.assertEquals(name,"tom");
    }

    @Test
    public void incr(){
        jedis.set("num","12");
        long incrnum = jedis.incrBy("num",12);

        System.out.println(incrnum);
    }

    @Test
    public void hash(){
        jedis.hset("school","student","tom");

        String classs = jedis.hget("school","student");
        System.out.println(classs);
    }

    @Test
    public void exist(){
        Boolean num = jedis.exists("name");

        System.out.println(num);
    }

    @Test
    public void hdel(){
        Long num = jedis.hdel("name1","class");
        System.out.println(num);
    }

    @Test
    public void find(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("name","tom");
        map.put("age","12");
        jedis.hmset("home",map);

        List<String> add = jedis.hmget("home","name","age");
        for(String ad : add){
            System.out.println(ad);
        }
    }

    @Test
    public void saveuser(){

    }

    @After
    public void close(){
        jedis.close();
    }

}
