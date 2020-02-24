package com.zy.aop;

import com.zy.aop.aspectj.NewDocumentarist;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestAspectJAnnotation {
    @Test
    public void testAspectJAnnotation(){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/aop/aspectj-annotation.xml");
        ctx.refresh();

        NewDocumentarist newDocumentarist = ctx.getBean("documentarist", NewDocumentarist.class);

        newDocumentarist.execute();

        ctx.close();
    }
}
