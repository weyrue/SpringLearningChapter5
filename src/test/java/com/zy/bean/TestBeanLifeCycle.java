package com.zy.bean;

import com.zy.bean.lifeCycle.MessageDigester;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeanLifeCycle {

    @Test
    public void testBeanLifeCycle() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/bean/lifeCycleConfig.xml");
//        ctx.registerShutdownHook();
        ctx.getBean("com.zy.bean.lifeCycle.LifeCycleBean#0");
//        ctx.close();
    }

    @Test
    public void testFactoryBean() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/bean/lifeCycleConfig.xml");

        MessageDigester digester = (MessageDigester) ctx.getBean("digester");
        digester.digest("Hello world!");

        ctx.close();
    }

    @Test
    public void testFactoryMethod(){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/bean/lifeCycleConfig.xml");

        MessageDigester digester = (MessageDigester) ctx.getBean("digester2");
        digester.digest("Hello world!");

        ctx.close();
    }
}
