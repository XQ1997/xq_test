package com.xu.service;

import com.github.pagehelper.PageInfo;
import com.xu.entity.Kaola;
import com.xu.entity.KaolaType;

import java.util.List;
import java.util.Map;

public interface KaolaService {

    /**
     * 保存商品
     * @param kaola
     */
    void savekaola(Kaola kaola);

    /**
     * 根据id查找商品
     * @param id
     * @return
     */
    Kaola findbyid(Integer id);

    /**
     * 查找商品类型
     * @return
     */
    List<KaolaType> findkaolaType();

    /**
     * 根据页数查找每页的数据
     * @param p
     * @return
     */
    PageInfo<Kaola> findKaolaByPageNo(Integer p);

    /**
     * 根据id删除商品
     * @param id
     */
    void delkaola(Integer id);

    /**
     * 编辑商品，根据传过来的id查询是否存在，然后进行修改
     * @param kaola
     */
    void editkaola(Kaola kaola);

    /**
     * 根据筛选条件的map集合进行筛选商品并进行分页
     * @param p
     * @param querymap
     * @return
     */
    PageInfo<Kaola> findKaolaByPageNowithquerymap(Integer p, Map<String,Object> querymap);
}
