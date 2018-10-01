package com.xq.test;

import com.xq.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

public class UserTest {

    @Test
    public void findByIdtest(){
        SqlSession sqlSession = null;
        try {
            //加载主配置文件
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            //创建sqlSessionFactory
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(reader);
            //创建sqlsession
            sqlSession = factory.openSession();
            //操作sql
            User user = sqlSession.selectOne("com.xq.mapper.UserMapper.findById",2);
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
}
