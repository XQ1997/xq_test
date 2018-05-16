package com.kaisheng.redis;

import com.kaisheng.entity.User;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
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
public class ProtostuffTest {
    @Autowired
    private JedisPool jedisPool;

    Jedis jedis = null;
    @Before
    public void init(){
        jedis = jedisPool.getResource();
    }

    @Test
    public void user(){
        User user = new User(1004,"康熙","北京",45);
        //序列化
        Schema<User> userSchema = RuntimeSchema.getSchema(User.class);
        byte[] bytes = ProtobufIOUtil.toByteArray(user,userSchema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

        jedis.set("user:1004".getBytes(),bytes);
        //反序列化
        byte[] bytes1 = jedis.get("user:1004".getBytes());
        User user1 = new User();
        ProtobufIOUtil.mergeFrom(bytes,user1,userSchema);
        System.out.println(user1);
    }

    @After
    public void destory(){
        jedis.close();
    }
}
