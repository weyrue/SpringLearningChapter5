package com.zy.aop.proxyfactorybean;

import org.aspectj.lang.JoinPoint;

public class SimpleAdvice {
    public void simpleBeforeAdvice(JoinPoint joinPoint) {
        System.out.println("Execute: "
                + joinPoint.getSignature().getDeclaringTypeName()
                + " "
                + joinPoint.getSignature().getName());
    }
}
