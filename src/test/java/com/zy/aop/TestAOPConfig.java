package com.zy.aop;

import com.zy.aop.proxyfactorybean.NewDocumentarist;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestAOPConfig {
    @Test
    public void testAOPConfig() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/aop/aopconfig.xml");
        ctx.refresh();

        NewDocumentarist newDocumentarist = ctx.getBean("documentarist", NewDocumentarist.class);

        newDocumentarist.execute();

        ctx.close();
    }
}
