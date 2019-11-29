package com.zy.aspectj.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class PreGreetingAspect {
    @Before("execution(* greetTo(..))")
    public void beforeGreeting() {
        System.out.println("How are you");
    }

    @Around("execution(* serveTo(..))")
    public void aroundServing(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(pjp.getSignature().toLongString());
        Object[] objs = new Object[1];
        objs[0] = "Tom";
        pjp.proceed(objs);
        System.out.println(pjp.getSignature().getName() + " is end.");
    }
}
