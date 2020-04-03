package com.zy.web.dao;

import com.zy.mysql.mybatis.page.Page;
import com.zy.web.base.BaseDao;
import com.zy.web.entities.SingerPO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SingerMapper extends BaseDao<SingerPO> {
}