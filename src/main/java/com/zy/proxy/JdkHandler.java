package com.zy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK代理模拟类
 *
 * @author Yi
 * @version 1.0
 * @since 5/7/2020
 */
public class JdkHandler<T> implements InvocationHandler {
    final private T target;

    public JdkHandler(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk代理开启");
        long startTime = System.currentTimeMillis();
        Object res = method.invoke(target, args);
        long endTime = System.currentTimeMillis();
        System.out.println("jdk代理结束，方法执行耗时" + (endTime - startTime) + "ms");
        return res;
    }
}
