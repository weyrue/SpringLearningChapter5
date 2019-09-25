package com.zy.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class GreetingInterceptor implements MethodInterceptor {
    //截获目标类方法的执行，并在前后添加横切逻辑
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        // 目标方法入参
        String clientName = (String) methodInvocation.getArguments()[0];
        // 在目标方法执行前调用
        System.out.println("How are you! Mr. " + clientName + ".");
        // 通过反射机制调用目标方法
        Object obj = methodInvocation.proceed();
        // 在目标方法后调用
        System.out.println("Please enjoy yourself!");
        return obj;
    }
}
