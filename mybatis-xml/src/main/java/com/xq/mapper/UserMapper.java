package com.xq.mapper;

import com.xq.entity.User;

import java.util.List;

public interface UserMapper {

    User findById(int id);
    List<User> findAll();
    void save(User user);
    void update(User user);
    void delete(int id);
}
