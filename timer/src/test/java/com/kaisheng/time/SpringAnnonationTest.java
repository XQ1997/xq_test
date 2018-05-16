package com.kaisheng.time;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-annonation-task.xml")
public class SpringAnnonationTest {

    @Test
    public void test() throws IOException {
        System.out.println("成功");
        System.in.read();
    }
}
