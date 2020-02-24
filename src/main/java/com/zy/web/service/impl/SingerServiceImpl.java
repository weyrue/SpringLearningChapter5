package com.zy.web.service.impl;

import com.zy.mysql.mybatis.page.Page;
import com.zy.web.dao.SingerMapper;
import com.zy.web.entities.SingerPO;
import com.zy.web.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("singerService")
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    @Override
    public List<SingerPO> findAll() {
        return singerMapper.findByList(new SingerPO());
    }

    @Override
    public SingerPO findById(Long id) {
        return singerMapper.get(id);
    }

    @Override
    public void save(SingerPO po) {
        singerMapper.insert(po);
    }

    @Override
    public List<SingerPO> findByPage(Page<SingerPO> page, SingerPO po) {
        return singerMapper.findBypage(page, po);
    }
}
