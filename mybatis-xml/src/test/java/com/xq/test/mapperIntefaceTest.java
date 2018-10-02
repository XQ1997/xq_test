package com.xq.test;

import com.xq.entity.User;
import com.xq.mapper.UserMapper;
import com.xq.util.FactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        User user = new User("董卿","深圳");
        int num = userMapper.save(user);
        System.out.println("保存成功");
        System.out.println("受影响的行数为：" + num);
        System.out.println("自动生成的主键值为：" + user.getId());
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

    @Test
    public void page(){
        List<User> userList = userMapper.page(0,3);
        for(User user : userList){
            System.out.println(user);
        }
    }

    @Test
    public void page1(){
        List<User> userList = userMapper.page1(0,3);
        for(User user : userList){
            System.out.println(user);
        }
    }

    @Test
    public void page2(){
        Map map = new HashMap<String,Integer>();
        map.put("start",0);
        map.put("size",3);
        List<User> userList = userMapper.page2(map);
        for(User user : userList){
            System.out.println(user);
        }
    }

    @After
    public void destory(){
        sqlSession.close();
    }
}
