package com.kaisheng.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaisheng.entity.Movie;
import com.kaisheng.entity.MovieExample;
import com.kaisheng.mapper.MovieMapper;
import com.kaisheng.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

     @Autowired
    private MovieMapper movieMapper;

    /**
     * 查询所有电影
     * @return
     */
    @Override
    public List<Movie> findAll() {
        return movieMapper.selectByExample(new MovieExample());
    }

    /**
     * 新增
     * @param movie
     */
    @Override
    public void save(Movie movie) {
       movieMapper.insertSelective(movie);
    }

    /**
     * 根据id查询电影
     * @param id
     * @return
     */
    @Override
    public Movie findByid(Integer id) {
        return movieMapper.selectByPrimaryKey(id);
    }

    /**
     * 编辑电影
     * @param movie
     */
    @Override
    public void update(Movie movie) {
        movieMapper.updateByPrimaryKey(movie);
    }

    /**
     * 删除电影
     * @param id
     */
    @Override
    public void del(Integer id)throws RuntimeException {
        Movie movie = movieMapper.selectByPrimaryKey(id);
        if(movie != null){
            movieMapper.deleteByPrimaryKey(id);
        }else{
            throw new RuntimeException("该电影不存在，删除失败");
        }
    }

    /**
     * 根据页数进行分页
     * @param pageNo
     * @return
     */
    @Override
    public PageInfo<Movie> findAllByPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,10);

        PageInfo<Movie> pageInfo = new PageInfo<>(movieMapper.selectByExample(new MovieExample()));
        return pageInfo;
    }
}
