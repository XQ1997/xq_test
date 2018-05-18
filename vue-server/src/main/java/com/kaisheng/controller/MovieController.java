package com.kaisheng.controller;

import com.github.pagehelper.PageInfo;
import com.kaisheng.entity.Movie;
import com.kaisheng.result.ResponseBean;
import com.kaisheng.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:8080")
public class MovieController {

    @Autowired
    private MovieService movieService;

    /**
     * 首页
     * @return
     */
    @GetMapping
    public ResponseBean list(@RequestParam(defaultValue = "1",required = false,name = "p")Integer PageNo){
        //List<Movie> movieList = movieService.findAll();

        PageInfo<Movie> moviePageInfo = movieService.findAllByPageNo(PageNo);
        return ResponseBean.success(moviePageInfo);
    }

    /**
     * 新增
     * @param movie 返过来的是json数据，需要使用注解@RequestBody，可以尝试进行json转化
     * @return
     */
    @PostMapping("/new")
    public ResponseBean save(@RequestBody Movie movie){
        movieService.save(movie);
        return ResponseBean.success();
    }

    @GetMapping("/{id}")
    public ResponseBean findByid(@PathVariable Integer id){
        Movie movie = movieService.findByid(id);
        return ResponseBean.success(movie);
    }

    @PutMapping("/edit/{id}")
    public ResponseBean edit(@RequestBody Movie movie){
        movieService.update(movie);
        return ResponseBean.success();
    }

    @DeleteMapping("/del/{id}")
    public ResponseBean del(@PathVariable Integer id){
        try {
            movieService.del(id);
            return ResponseBean.success();
        }catch(RuntimeException e){
            return ResponseBean.error(e.getMessage());
        }
    }
}
