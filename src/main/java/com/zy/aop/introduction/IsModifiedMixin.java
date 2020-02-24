package com.zy.aop.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class IsModifiedMixin extends DelegatingIntroductionInterceptor implements IsModified {
    private boolean isModified = false;
    private Map<Method, Method> methodCache = new HashMap<>();

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        if (!isModified) {
            if (mi.getMethod().getName().startsWith("set") && mi.getArguments().length == 1) {
                Method getter = getGetter(mi.getMethod());
                if (getter != null) {
                    Object newVal = mi.getArguments()[0];
                    Object oldVal = getter.invoke(mi.getThis(), null);
                    if (newVal == null && oldVal == null) {
                        isModified = false;
                    } else if (newVal == null || oldVal == null) {
                        isModified = true;
                    } else {
                        isModified = !newVal.equals(oldVal);
                    }
                }
            }
        }
        return super.invoke(mi);
    }

    @Override
    public boolean isModified() {
        return isModified;
    }

    private Method getGetter(Method setter) {
        Method getter = methodCache.get(setter);

        if (getter != null) return getter;
        String getterName = setter.getName().replaceFirst("set", "get");
        try {
            getter = setter.getDeclaringClass().getMethod(getterName, null);
            synchronized (methodCache) {
                methodCache.put(setter, getter);
            }
            return getter;
        } catch (NoSuchMethodException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
