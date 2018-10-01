package com.xq.test;

import com.xq.entity.User;
import com.xq.util.FactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class UserTest {

    private SqlSession sqlSession = null;

    @Before
    public void init(){
        sqlSession = FactoryUtil.getSqlSession();
    }

    @Test
    public void findByIdtest(){
        User user = sqlSession.selectOne("com.xq.mapper.UserMapper.findById",2);
        System.out.println(user);
    }

    @Test
    public void findAlltest(){
        List<User> userList = sqlSession.selectList("com.xq.mapper.UserMapper.findAll");
        for(User user : userList){
            System.out.println(user);
        }
    }

    @After
    public void destory(){
        sqlSession.close();
    }
}
