package com.zy.mysql.jdbc;

import com.zy.mysql.mybatis.page.Page;
import com.zy.web.entities.SingerPO;
import com.zy.web.service.SingerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/mysql/spring-transaction.xml"})
public class TestTransaction {
    private static final Logger log = LoggerFactory.getLogger(TestTransaction.class);

    @Autowired
    private SingerService singerService;

    @Test
    public void testInsertTransaction() {
        SingerPO po = new SingerPO();
        po.setFirstName("Zhanchao");
        po.setLastName("Chen");
        Calendar calendar = Calendar.getInstance();
        calendar.set(1992, Calendar.MAY, 13);
        po.setBirthDate(calendar.getTime());
        po.setVersion(0);
        singerService.save(po);
    }

    @Test
    public void testLog4j() {
        List<SingerPO> list = singerService.findAll();
//        Page<SingerPO> page = new Page<>();

//        List<SingerPO> list = singerService.findByPage(page, new SingerPO());

//        for (SingerPO po : list) {
//            log.info(po.toString());
//        }
    }
}
