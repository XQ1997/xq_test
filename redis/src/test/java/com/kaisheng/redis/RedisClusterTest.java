package com.kaisheng.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class RedisClusterTest {

    JedisCluster jedisCluster = null;

    @Before
    public void init(){
        //连接池的配置
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        config.setMinIdle(5);

        //集群主机集合
        Set<HostAndPort> hostAndPorts = new HashSet<HostAndPort>();
        hostAndPorts.add(new HostAndPort("192.168.248.133",6001));
        hostAndPorts.add(new HostAndPort("192.168.248.133",6002));
        hostAndPorts.add(new HostAndPort("192.168.248.134",6001));
        hostAndPorts.add(new HostAndPort("192.168.248.134",6002));
        hostAndPorts.add(new HostAndPort("192.168.248.135",6001));
        hostAndPorts.add(new HostAndPort("192.168.248.135",6002));

        jedisCluster = new JedisCluster(hostAndPorts,config);
    }

    @Test
    public void set(){
        jedisCluster.set("test","hello cluster");

        String test = jedisCluster.get("test");
        System.out.println(test);
    }

    @After
    public void destory() throws IOException {
        jedisCluster.close();
    }
}
