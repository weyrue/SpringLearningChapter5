package com.zy.aop.beforeadvice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SecurityAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        UserInfo userInfo = securityManager.getLoggedOnUser();

        if (userInfo == null) {
            System.out.println("No user authenticated");
            throw new SecurityException("You must login before attempting " +
                    "to invoke the method: " +
                    method.getName());
        } else if ("John".equals(userInfo.getUsername())) {
            System.out.println("Logged in User is John - OKAY!");
        } else {
            System.out.println("Login user is: " + userInfo.getUsername() +
                    ". Not goods.:(");
            throw new SecurityException("User " + userInfo.getUsername() +
                    " is not allowed access to method " + method.getName());
        }
    }

    private SecurityManager securityManager;

    public SecurityAdvice() {
        this.securityManager = new SecurityManager();
    }
}
