package com.zy.proxy;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

import java.util.LinkedList;

/**
 * 用Enhancer生成代理类，再生成之前设置目标类和拦截器
 *
 * @author Yi
 * @version 1.0
 * @since 5/7/2020
 */
@SuppressWarnings("unchecked")
public class CglibInterceptorTest {
    @Test
    public void testCglibInterceptor() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(LinkedList.class);
        enhancer.setCallback(new CglibInterceptor());
        LinkedList<String> proxy = (LinkedList<String>) enhancer.create();
        proxy.add("Hello World");
    }

}
