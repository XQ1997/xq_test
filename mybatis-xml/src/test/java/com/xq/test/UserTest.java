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
        sqlSession = FactoryUtil.getSqlSession(true);
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

    @Test
    public void savetest(){
        User user = new User("哪吒","成都");
        sqlSession.insert("com.xq.mapper.UserMapper.save",user);
        //sqlSession.commit();
        //sqlSession.rollback();
        System.out.println("保存成功");
    }

    @Test
    public void deletetest(){
        sqlSession.delete("com.xq.mapper.UserMapper.delete",3);
        System.out.println("删除成功");
    }

    @Test
    public void updatetest(){
        User user = sqlSession.selectOne("com.xq.mapper.UserMapper.findById",4);
        if(user != null){
            user.setUserAddress("海南");
            sqlSession.update("com.xq.mapper.UserMapper.update",user);
            System.out.println("修改成功");
        }else {
            System.out.println("数据不存在");
        }
    }

    @After
    public void destory(){
        sqlSession.close();
    }
}
