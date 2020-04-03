package com.zy.mysql.jdbc;

import com.zy.web.entities.SingerPO;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TestPlainSingerDao {
    @Test
    public void testPlainSingerDao() {
        PlainSingerDao dao = new PlainSingerDao();
        List<SingerPO> list = dao.findAll();

        for (SingerPO po : list) {
            System.out.println(po);
        }

//        System.out.println(list);
    }
}
