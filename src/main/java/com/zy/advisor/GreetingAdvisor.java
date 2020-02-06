package com.zy.advisor;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.Method;
import java.util.*;

public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor {
    @Override
    public boolean matches(Method method, Class<?> aClass) {
        return "greetTo".equals(method.getName());
    }

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> aClass) {
                return Waiter.class.isAssignableFrom(aClass);
            }
        };
    }

    public static void main(String[] args) {

        System.runFinalization();
    }


}


