package com.zy.web.service;

import com.zy.mysql.mybatis.page.Page;
import com.zy.web.entities.SingerPO;

import java.util.List;

public interface SingerService {
    List<SingerPO> findAll();

    SingerPO findById(Long id);

    void save(SingerPO po);

    List<SingerPO> findByPage(Page<SingerPO> page,SingerPO po);
}
