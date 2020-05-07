package com.zy.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib代理需要实现方法拦截器
 * 调用方法是需要调用methodProxy.invokeSuper方法，否则会会再次进入此拦截器
 *
 * @author Yi
 * @version 1.0
 * @since 5/7/2020
 */
public class CglibInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib代理开启");
        long startTime = System.currentTimeMillis();
        // 只能调用methodProxy代理执行方法，如果调用method执行方法则会再次进入此拦截器
//        Object res = methodProxy.invoke(o, objects);
        Object res = methodProxy.invokeSuper(o, objects);
        long endTime = System.currentTimeMillis();
        System.out.println("Cglib代理结束，方法执行耗时" + (endTime - startTime) + "ms");
        return res;
    }
}
