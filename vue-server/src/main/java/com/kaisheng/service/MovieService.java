package com.kaisheng.service;

import com.github.pagehelper.PageInfo;
import com.kaisheng.entity.Movie;

import java.util.List;

public interface MovieService {
    /**
     * 查询所有电影
     * @return
     */
    List<Movie> findAll();

    /**
     * 新增
     * @param movie
     */
    void save(Movie movie);

    /**
     * 根据id查询电影
     * @param id
     * @return
     */
    Movie findByid(Integer id);

    /**
     * 编辑电影
     * @param movie
     */
    void update(Movie movie);

    /**
     * 删除电影
     * @param id
     */
    void del(Integer id)throws RuntimeException;

    /**
     * 根据页数进行分页
     * @param pageNo
     * @return
     */
    PageInfo<Movie> findAllByPageNo(Integer pageNo);
}
