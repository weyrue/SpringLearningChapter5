package com.zy.bean;

import com.zy.bean.propertyEditor.DefaultPropertyEditor;
import com.zy.bean.propertyEditor.MyEditorSample;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestPropertyEditor {

    @Test
    public void testDefaultPropertyEditor() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/bean/property-editor.xml");
        ctx.refresh();

        DefaultPropertyEditor dpe = (DefaultPropertyEditor) ctx.getBean("buildInSample");
        System.out.println(dpe);

        ctx.close();
    }

    @Test
    public void testMyEditorSample() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/bean/property-editor.xml");
        ctx.refresh();

        MyEditorSample mySample = (MyEditorSample) ctx.getBean("mySample");
        System.out.println(mySample);

        ctx.close();
    }
}
