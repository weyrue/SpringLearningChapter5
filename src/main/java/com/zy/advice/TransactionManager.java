package com.zy.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class TransactionManager implements ThrowsAdvice {
    public void afterThrowing(Method method, Object[] args, Object target, Exception e) throws Throwable {
        System.out.println("----------");
        System.out.println("method:" + method.getName());
        System.out.println("抛出异常:" + e.getMessage());
        System.out.println("成功回滚事务。");
    }
}
