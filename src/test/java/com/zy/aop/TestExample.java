package com.zy.aop;

import com.zy.aop.example.Agent;
import com.zy.aop.example.AgentDecorator;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

public class TestExample {
    @Test
    public void testExample() {
        ProxyFactory proxyFactory = new ProxyFactory();
        Agent target = new Agent();
        proxyFactory.addAdvice(new AgentDecorator());
        proxyFactory.setTarget(target);

        Agent proxy = (Agent) proxyFactory.getProxy();
        target.say();
        System.out.println();
        proxy.say();

    }
}
