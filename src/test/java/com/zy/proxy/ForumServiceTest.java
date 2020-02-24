package com.zy.proxy;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class ForumServiceTest {
    @Test
    public void proxy() {
        ForumService target = new ForumServiceImpl();

        PerformanceHandler handler = new PerformanceHandler(target);

        ForumService proxy = (ForumService) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), handler);
        proxy.removeForum(10);
        proxy.removeTopic(1012);
    }

    @Test
    public void cglibProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ForumServiceImpl.class);
        enhancer.setCallback(new CglibProxy());
        ForumServiceImpl forumService = (ForumServiceImpl) enhancer.create();
        forumService.removeForum(10);
        forumService.removeTopic(1012);
    }
}
