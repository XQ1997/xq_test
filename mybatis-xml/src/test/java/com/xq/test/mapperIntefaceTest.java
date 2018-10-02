package com.xq.test;

import com.xq.entity.User;
import com.xq.mapper.UserMapper;
import com.xq.util.FactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class mapperIntefaceTest {
    private SqlSession sqlSession = null;
    private UserMapper userMapper = null;

    @Before
    public void init(){
        sqlSession = FactoryUtil.getSqlSession(true);
        //动态代理    动态生成接口的实现类
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void findByIdtest(){
        User user = userMapper.findById(4);
        System.out.println(user);
    }

    @Test
    public void findAllTest(){
        List<User> userList = userMapper.findAll();
        for(User user : userList){
            System.out.println(user);
        }
    }

    @Test
    public void savetest(){
        User user = new User("董伟","深圳");
        userMapper.save(user);
        System.out.println("保存成功");
    }

    @Test
    public void deletetest(){
        userMapper.delete(2);
        System.out.println("删除成功");
    }

    @Test
    public void updateTest(){
        User user = userMapper.findById(4);
        if(user != null){
            user.setUserName("杨丹");
            userMapper.update(user);
            System.out.println("修改成功");
        }else{
            System.out.println("查无此人");
        }

    }

    @After
    public void destory(){
        sqlSession.close();
    }
}
