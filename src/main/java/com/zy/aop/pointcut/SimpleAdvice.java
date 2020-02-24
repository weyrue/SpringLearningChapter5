package com.zy.aop.pointcut;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SimpleAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("Only good guitarists invoke.");
        Object retVal = methodInvocation.proceed();
        System.out.println(">> Done\n");
        return retVal;
    }
}
