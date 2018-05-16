package com.kaisheng.service;

import com.github.pagehelper.PageInfo;
import com.kaisheng.entity.Product;

public interface ProductService {
    PageInfo<Product> findAll(Integer pageNo);

    void save(Product product);

    Product findByid(Integer id);

    void update(Product product);

    void del(Integer id)throws RuntimeException;
}
