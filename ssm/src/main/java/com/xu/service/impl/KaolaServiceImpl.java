package com.xu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.xu.entity.Kaola;
import com.xu.entity.KaolaType;
import com.xu.mapper.KaolaMapper;
import com.xu.mapper.KaolaTypeMapper;
import com.xu.service.KaolaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.*;

@Service
public class KaolaServiceImpl implements KaolaService {

    @Autowired
    private KaolaMapper kaolaMapper;
    @Autowired
    private KaolaTypeMapper kaolaTypeMapper;
  /*  @Autowired
    private JedisPool jedisPool;*/
    @Autowired
    private CacheManager cacheManager;

    private Logger logger = LoggerFactory.getLogger(KaolaServiceImpl.class);

    /**
     * 先将热数据加载，在程序启动之前
     */
    @PostConstruct
    public void init(){
        Cache cache = cacheManager.getCache("kaola");
        Kaola kaola = kaolaMapper.findbyid(30930);
        cache.put(30930,kaola);
        logger.info("kaola:{}",kaola);
    }

    /**
     *保存商品
     * @param kaola
     */
    @Override
    public void savekaola(Kaola kaola) {
        kaola.setCommentNum(Kaola.DEFAULT_COMMENT_NUM);
        kaolaMapper.save(kaola);
        logger.info("保存商品为:{}",kaola);
    }

    /**
     * 根据id查找商品
     * @param id
     * @return
     */
    @Override
    @Cacheable("kaola")
    public Kaola findbyid(Integer id) {
       /* final String KEY = "kaola" + id;
        Kaola kaola = null;
        Jedis jedis = jedisPool.getResource();
        if(jedis.exists(KEY)){
            String key = jedis.get(KEY);
            kaola = new Gson().fromJson(key,Kaola.class);
        }else{
            kaola = kaolaMapper.findbyid(id);
            String json = new Gson().toJson(kaola);
            jedis.set(KEY,json);
        }
        jedis.close();
        return kaola;*/

            return kaolaMapper.findbyid(id);
    }

    /**
     * 查找商品类型
     * @return
     */
    @Override
    public List<KaolaType> findkaolaType() {
       /* final String KEY = "kaolaTypeList";
        List<KaolaType> kaolaTypeList = null;
        Jedis jedis = jedisPool.getResource();
        if(jedis.exists(KEY)){
            String list = jedis.get("kaolaTypeList");
            KaolaType[] fromJson = new Gson().fromJson(list,KaolaType[].class);
            kaolaTypeList = Arrays.asList(fromJson);
        }else{
            kaolaTypeList = kaolaTypeMapper.findType();
            String json = new Gson().toJson(kaolaTypeList);
            jedis.set("kaolaTypeList",json);
        }
        jedis.close();
        return kaolaTypeList;*/

        return  kaolaTypeMapper.findType();
    }

    /**
     * 根据页数查找每页的数据
     * @param p
     * @return
     */
    @Override
    public PageInfo<Kaola> findKaolaByPageNo(Integer p) {
        PageHelper.startPage(p,25);
        List<Kaola> kaolaList = kaolaMapper.findAllwithType();

        return new PageInfo<>(kaolaList);
    }

    /**
     * 根据id删除商品
     * @param id
     */
    @Override
    public void delkaola(Integer id) {
        Kaola kaola = kaolaMapper.findbyid(id);
        if(kaola != null){
            kaolaMapper.del(id);
            logger.info("删除商品：{}" + kaola);
        }
    }

    /**
     * 编辑商品
     * @param kaola
     */
    @Override
    @CachePut("kaola")
    public void editkaola(Kaola kaola) {

        if(kaola != null){
            kaolaMapper.edit(kaola);
            logger.info("修改后商品为：{}" + kaola);
        }
    }

    /**
     * 根据筛选条件的map集合进行筛选商品并进行分页
     *
     * @param p
     * @param querymap
     * @return
     */
    @Override
    public PageInfo<Kaola> findKaolaByPageNowithquerymap(Integer p, Map<String, Object> querymap) {
        PageHelper.startPage(p,15);
        List<Kaola> kaolaList = kaolaMapper.findAllwithTypewithquerymap(querymap);

        return new PageInfo<>(kaolaList);
    }
}
