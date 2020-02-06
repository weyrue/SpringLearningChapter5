package com.zy.bean;

import com.zy.bean.methodReplacer.Boss1;
import com.zy.bean.methodReplacer.MagicBoss;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class TestMethodReplacer {
    @Test
    public void testLookup() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring-bean.xml");
        MagicBoss magicBoss = (MagicBoss) ac.getBean("magicBoss");

        magicBoss.getCar().introduce();
    }

    @Test
    public void testMethodReplacer() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring-bean.xml");
        Boss1 boss1 = (Boss1) ac.getBean("boss1");

        boss1.getCar().introduce();
    }

    @Test
    public void testCharset(){
        String s = "Äú²éÕÒµÄ×ÊÔ´´æÔÚÎÊÌâ£¬Òò¶øÎÞ·¨ÏÔÊ¾¡£";

        try {
            System.out.println(new String(s.getBytes("GB2312"),StandardCharsets.UTF_8));
            System.out.println(new String(s.getBytes(StandardCharsets.UTF_8),"GB2312"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
