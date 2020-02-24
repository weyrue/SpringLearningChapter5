package com.zy.aop.pointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> aClass) {
//        return "GoodGuitarist".equals(aClass.getSimpleName()) && "sing".equals(method.getName());
        return "sing".equals(method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {
        return aClass -> "GoodGuitarist".equals(aClass.getSimpleName());
    }
}
