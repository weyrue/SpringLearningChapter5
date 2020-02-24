package com.zy.aop.pointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

public class SimpleDynamicPointCut extends DynamicMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> aClass, Object... objects) {
//        System.out.println("Dynamic check");
        return objects.length == 0 || ((String) objects[0]).length() > 5;
    }

    @Override
    public ClassFilter getClassFilter() {
//        System.out.println("Static class check");
        return aClass -> GoodGuitarist.class.equals(aClass);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
//        System.out.println("Static method check");
        return "sing".equals(method.getName());
    }
}
