package com.kaisheng.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaisheng.entity.Product;
import com.kaisheng.entity.ProductExample;
import com.kaisheng.mapper.ProductMapper;
import com.kaisheng.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

   @Autowired
   private ProductMapper productMapper;

    @Override
    public PageInfo<Product> findAll(Integer pageNo) {
        PageHelper.startPage(pageNo,10);

        ProductExample productExample = new ProductExample();
         List<Product> productList = productMapper.selectByExample(productExample);

        return new PageInfo<>(productList);
    }

    @Override
    public void save(Product product) {
        productMapper.insertSelective(product);
    }

    @Override
    public Product findByid(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public void del(Integer id) throws RuntimeException {
        Product product = productMapper.selectByPrimaryKey(id);
        if(product != null){
            productMapper.deleteByPrimaryKey(id);
        }else {
            throw new RuntimeException("该商品不存在");
        }
    }
}
