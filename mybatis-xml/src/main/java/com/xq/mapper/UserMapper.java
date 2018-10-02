package com.xq.mapper;

import com.xq.entity.Student;
import com.xq.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User findById(int id);
    List<User> findAll();
    int save(User user);
    void update(User user);
    void delete(int id);
    List<User> page(int start,int page);
    List<User> page1(@Param("start") int start,@Param("size") int page);
    List<User> page2(Map<String,Integer> map);

    Student findByIdwithclass(int id);
    Student findByIdwithtag(int id);
}
