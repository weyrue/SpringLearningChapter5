package com.zy.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AnnotatedAdvice {
    @Pointcut(value = "execution(* *..sing*(*..String)) && args(lyric)")
    public void singExecution(String lyric) {
    }

    @Pointcut(value = "bean(john*)")
    public void isJohn() {
    }

    @Before(value = "singExecution(lyric) && isJohn()", argNames = "joinPoint,lyric")
    public void simpleBeforeAdvice(JoinPoint joinPoint, String lyric) {
        System.out.println("Before advice - "
                + "Execute: "
                + joinPoint.getSignature().getDeclaringTypeName()
                + " "
                + joinPoint.getSignature().getName());

    }

    @Around(value = "singExecution(lyric) && isJohn()")
    public Object simpleAroundAdvice(ProceedingJoinPoint proceedingJoinPoint, String lyric) throws Throwable {
        System.out.println("Around advice(before) - "
                + "Execute: "
                + proceedingJoinPoint.getSignature().getDeclaringTypeName()
                + " "
                + proceedingJoinPoint.getSignature().getName());

        Object retValue = proceedingJoinPoint.proceed();

        System.out.println("Around advice(After) - "
                + "Execute: "
                + proceedingJoinPoint.getSignature().getDeclaringTypeName()
                + " "
                + proceedingJoinPoint.getSignature().getName());

        return retValue;
    }

}
