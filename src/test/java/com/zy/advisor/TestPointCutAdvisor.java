package com.zy.advisor;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPointCutAdvisor {
    @Test
    public void testPointcutAdvisor() {
        String configPath = "spring/spring-pointcut.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);

        Waiter waiter = (Waiter) ctx.getBean("waiter");
        Seller seller = (Seller) ctx.getBean("seller");

        waiter.greetTo("John");
        waiter.serveTo("John");
        seller.greetTo("John");
    }

    @Test
    public void testRegexPointcutAdvisor() {
        String configPath = "spring/spring-pointcut.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);

        Waiter waiter2 = (Waiter) ctx.getBean("waiter2");
        waiter2.greetTo("John");
        waiter2.serveTo("John");
    }

    @Test
    public void testDynamicPointcutAdvisor() {
        String configPath = "spring/spring-pointcut.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);

        Waiter waiter3 = (Waiter) ctx.getBean("waiter3");

        waiter3.serveTo("Peter");
        waiter3.greetTo("Peter");
        waiter3.serveTo("John");
        waiter3.greetTo("John");
    }

    @Test
    public void testControlFlowPointcutAdvisor() {
        String configPath = "spring/spring-pointcut.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);

        Waiter waiter4 = (Waiter) ctx.getBean("waiter4");
        WaiterDelegate waiterDelegate = new WaiterDelegate();
        waiterDelegate.setWaiter(waiter4);

        waiter4.serveTo("Peter");
        waiter4.greetTo("Peter");

        waiterDelegate.service("Peter");
    }

    @Test
    public void testComposablePointcutAdvisor() {
        String configPath = "spring/spring-pointcut.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);

        Waiter waiter5 = (Waiter) ctx.getBean("waiter5");
        WaiterDelegate waiterDelegate = new WaiterDelegate();
        waiterDelegate.setWaiter(waiter5);

        waiter5.serveTo("Peter");
        waiter5.greetTo("Peter");

        waiterDelegate.service("Peter");
    }
}
