package com.zy.aspectj.aspectj;

import com.zy.advisor.Waiter;
import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AspectJProxyTest {

    @Test
    public void proxyTest() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");

        Waiter proxy = ac.getBean(Waiter.class);
        proxy.greetTo("John");
        proxy.serveTo("John");

//        Waiter target = new Waiter();
//        AspectJProxyFactory factory = new AspectJProxyFactory();
//
//        factory.setTarget(target);
//
//        factory.addAspect(PreGreetingAspect.class);
//
//        Waiter proxy = factory.getProxy();
//        proxy.greetTo("John");
//        proxy.serveTo("John");
    }
}
