package com.xq.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class FactoryUtil {
    private static SqlSessionFactory factory;
    private static SqlSession sqlSession;
    static {
        try {
            //加载主配置文件
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            //创建sqlSessionFactory
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            factory = builder.build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getFactory() {
        return factory;
    }

    public static SqlSession getSqlSession(boolean auto) {
        return factory.openSession(auto);
    }
}
