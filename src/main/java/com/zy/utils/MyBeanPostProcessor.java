package com.zy.utils;

import com.zy.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization..." + beanName + "..." + bean + "...");
        return bean;
//        ClassPathXmlApplicationContext
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        final Object obj = bean;
        if (bean instanceof UserService) {
            Object proxyObj = Proxy.newProxyInstance(this.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("pre testLogger");
                    Object result = method.invoke(obj, args);
                    System.out.println("post testLogger");
                    return result;
                }
            });
            return proxyObj;
        }
        return obj;
    }
}
