package com.zy.mysql.mybatis;

import com.zy.web.dao.SingerMapper;
import com.zy.web.entities.SingerPO;
import com.zy.mysql.mybatis.page.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/mysql/spring-mysql.xml"})
public class TestMybatis {

    @Autowired
    private SingerMapper singerMapper;

    @Test
    public void testMybatis() {

        Page<SingerPO> page = new Page<>(1, 7);
        SingerPO po = new SingerPO();
        po.setId(1L);
//        po.setLastName("Mayer1");
        po.setOrderBy("id asc");
//        Page<SingerPO> page = new Page<>();
//        List<SingerPO> list = singerMapper.findByList(page, po);
        List<SingerPO> list = singerMapper.findByList(po);
//        singerMapper.updateByPrimaryKeySelective(po);

        System.out.println(list);

    }
}
