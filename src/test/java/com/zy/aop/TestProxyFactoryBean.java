package com.zy.aop;

import com.zy.aop.introduction.Contact;
import com.zy.aop.introduction.IsModified;
import com.zy.aop.proxyfactorybean.Documentarist;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestProxyFactoryBean {
    @Test
    public void testProxyFactoryBean() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/aop/proxyfactorybean.xml");
        ctx.refresh();
        Documentarist documentaristOne = (Documentarist) ctx.getBean("documentaristOne");
        Documentarist documentaristTwo = ctx.getBean("documentaristTwo", Documentarist.class);

        documentaristOne.execute();
        System.out.println();
        documentaristTwo.execute();

        ctx.close();
    }

    @Test
    public void testProxyFactoryBeanIntroduction() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/aop/proxyfactorybean.xml");
        ctx.refresh();

        Contact contact = (Contact) ctx.getBean("proxyThree");
        IsModified proxy = (IsModified) contact;
        System.out.println(proxy.isModified());
        contact.setName("Yi Zhang");
        System.out.println(proxy.isModified());

        ctx.close();
    }
}
