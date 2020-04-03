package com.zy.mysql.jdbc;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/mysql/spring-jdbc.xml"})
public class TestJdbcTemplate {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testJdbcTemplate() {
        Assert.assertNotNull(jdbcTemplate);
        String result = jdbcTemplate.queryForObject("select concat(first_name,' ',last_name) from singer where id = ?", new Object[]{Long.valueOf(2L)}, String.class);
        System.out.println(result);
        Assert.assertTrue("Eric Clapton".equals(result));
    }

}
