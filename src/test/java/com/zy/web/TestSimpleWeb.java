package com.zy.web;

import com.zy.web.service.SingerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring/web/spring-simple-web.xml"})
public class TestSimpleWeb {
    @Autowired
    private SingerService singerService;

    @Test
    public void testSimpleWeb() {
        System.out.println(singerService.findAll());
    }

}
