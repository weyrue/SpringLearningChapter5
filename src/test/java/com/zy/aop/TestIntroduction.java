package com.zy.aop;

import com.zy.aop.introduction.Contact;
import com.zy.aop.introduction.IsModified;
import com.zy.aop.introduction.IsModifiedAdvisor;
import com.zy.aop.introduction.IsModifiedMixin;
import org.junit.Test;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;

public class TestIntroduction {
    @Test
    public void testDefaultIntroduction() {
        Contact target = new Contact();
        target.setName("John Mayer");

        IntroductionAdvisor introductionAdvisor = new IsModifiedAdvisor();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(introductionAdvisor);
        proxyFactory.setOptimize(true);

        Contact proxy = (Contact) proxyFactory.getProxy();
        IsModified proxyInstance = (IsModified) proxy;

        System.out.println("proxy instanceof Contact: " + (proxy instanceof Contact));
        System.out.println("proxy instanceof IsModified: " + (proxy instanceof IsModified));
        System.out.println("proxyInstance.isModified(): " + proxyInstance.isModified());

        proxy.setName("John Mayer");
        System.out.println("proxyInstance.isModified(): " + proxyInstance.isModified());

        proxy.setName("Yi Zhang");
        System.out.println("proxyInstance.isModified(): " + proxyInstance.isModified());
    }
}
