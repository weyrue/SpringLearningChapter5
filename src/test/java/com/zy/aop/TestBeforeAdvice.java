package com.zy.aop;

import com.zy.aop.beforeadvice.SecurityManager;
import com.zy.aop.beforeadvice.*;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

public class TestBeforeAdvice {
    @Test
    public void testSimpleBeforeAdvice() {
        Guitarist target = new Guitarist();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(new SimpleBeforeAdvice());

        Guitarist proxy = (Guitarist) proxyFactory.getProxy();

        proxy.sing();
    }

    @Test
    public void testSecurityManager() {
        SecurityManager securityManager = new SecurityManager();
        SecureBean secureBean = getSecureBean();

        securityManager.login("John", "pwd");
        secureBean.writeSecureMessage();
        securityManager.logout();

        try {
            securityManager.login("invalid user", "pwd");
            secureBean.writeSecureMessage();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            securityManager.logout();
        }

        try {
            secureBean.writeSecureMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private SecureBean getSecureBean() {
        SecurityAdvice securityAdvice = new SecurityAdvice();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new SecureBean());
        proxyFactory.addAdvice(securityAdvice);
        SecureBean proxy = (SecureBean) proxyFactory.getProxy();
        return proxy;
    }
}
