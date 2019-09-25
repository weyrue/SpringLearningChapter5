package com.zy.advice;

import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeforeAdviceTest {

    @Test
    public void testBefore() {
        Waiter waiter = new NaiveWaiter();
        BeforeAdvice beforeAdvice = new GreetingBeforeAdvice();
        // spring提供的代理工厂
        ProxyFactory proxyFactory = new ProxyFactory();
        // 设置目标代理
        proxyFactory.setOptimize(true);
        proxyFactory.setInterfaces(waiter.getClass().getInterfaces());
        proxyFactory.setTarget(waiter);
        // 为代理目标添加增强
        proxyFactory.addAdvice(beforeAdvice);
        // 生成代理实例
        Waiter proxy = (Waiter) proxyFactory.getProxy();
        proxy.greetTo("John");
        proxy.serveTo("Tom");
    }

    @Test
    public void testBeforeApplicationContext() {
        String configPath = "spring/spring-aop.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter) ctx.getBean("waiter");
        waiter.greetTo("John");
    }

    @Test
    public void testAroundApplicationContext() {
        String configPath = "spring/spring-aop.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        Waiter waiter = (Waiter) ctx.getBean("waiter2");
        waiter.greetTo("John");
    }

    @Test
    public void testTransactionManagerApplicationContext() {
        String configPath = "spring/spring-aop.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        ForumService forumService = (ForumService) ctx.getBean("forumService");
        try {
            forumService.removeForum(1);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        try {
            forumService.updateForum(new Forum());
        } catch (Exception e) {
//            e.printStackTrace();
        }
    }
}
