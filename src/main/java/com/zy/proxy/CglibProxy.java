package com.zy.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        PerformanceMonitor.begin(o.getClass().getName() + "." + method.getName());
        // 只能调用methodProxy代理执行方法，如果调用method执行方法则会再次进入此拦截器
//        method.invoke(o, objects);
        Object result = methodProxy.invokeSuper(o, objects);
        PerformanceMonitor.end();

        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        return result;
    }
}
