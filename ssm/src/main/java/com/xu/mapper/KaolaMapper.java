package com.xu.mapper;

import com.github.pagehelper.PageInfo;
import com.xu.entity.Kaola;

import java.util.List;
import java.util.Map;

public interface KaolaMapper {

    void save(Kaola kaola);

    Kaola findbyid(Integer id);

    List<Kaola> findAllwithType();

    void del(Integer id);

    void edit(Kaola kaola);

    List<Kaola> findAllwithTypewithquerymap(Map<String, Object> querymap);
}
