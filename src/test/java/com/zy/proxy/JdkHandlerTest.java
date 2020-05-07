package com.zy.proxy;

import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试JDK代理
 * 创建目标对象和handler对象，再利用Proxy包装生成代理
 *
 * @version 1.0
 * @author Yi
 * @since 5/7/2020
 */
public class JdkHandlerTest {

    @Test
    public void testJdkHandler() {
        List<String> target = new ArrayList<>();
        JdkHandler<List> handler = new JdkHandler<>(target);

        List<String> proxy = (List<String>)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);

        proxy.add("Hello World");
        System.out.println(proxy);
    }
}
