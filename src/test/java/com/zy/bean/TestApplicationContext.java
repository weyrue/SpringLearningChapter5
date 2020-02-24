package com.zy.bean;

import com.zy.applicationcontext.event.MessagePublisher;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class TestApplicationContext {

    @Test
    public void testMessageSource() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/application-context/message-source.xml");
        ctx.refresh();

        Locale english = Locale.ENGLISH;
        Locale german = new Locale("de", "DE");

        System.out.println(ctx.getMessage("msg", null, english));
        System.out.println(ctx.getMessage("msg", null, german));

        System.out.println(ctx.getMessage("nameMsg", new Object[]{"John", "Mayer"}, english));
        System.out.println(ctx.getMessage("nameMsg", new Object[]{"John", "Mayer"}, german));

        ctx.close();
    }

    @Test
    public void testEvent() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/application-context/message-event.xml");
        ctx.refresh();

        MessagePublisher publisher = (MessagePublisher) ctx.getBean("publisher");

        publisher.publishEvent("I send an SOS to the world...");
        publisher.publishEvent("...I hope someone gets my...");
        publisher.publishEvent("...Message in a bottle...");

        ctx.close();
    }

    @Test
    public void testResourceDemo() throws Exception {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();

        File file = File.createTempFile("test", ".txt");
        file.deleteOnExit();

        //注意windows和linux差异
        //linux：file:/
        Resource res1 = ctx.getResource("file:" + file.getPath());
        System.out.println(res1.getClass());
        System.out.println(res1.getURL().getContent());
        System.out.println();

        Resource res2 = ctx.getResource("classpath:test.txt");
        System.out.println(res2.getClass());
        System.out.println(res2.getURL().getContent());
        System.out.println();

        Resource res3 = ctx.getResource("http://www.baidu.com");
        System.out.println(res3.getClass());
        System.out.println(res3.getURL().getContent());
        System.out.println();

    }

    @Test
    public void testPropertyResource() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/application-context/property-resource.xml");
        ctx.refresh();

        ConfigurableEnvironment env = ctx.getEnvironment();
        MutablePropertySources propertySources = env.getPropertySources();

        Map<String, Object> appMap = new HashMap<>();
        appMap.put("user.home", "application_home");

        propertySources.addLast(new MapPropertySource("prospring5 MAP", appMap));
//        propertySources.addFirst(new MapPropertySource("prospring5 MAP", appMap));

        System.out.println("user.home: " + System.getProperty("user.home"));
        System.out.println("JAVA_HOME: " + System.getenv("JAVA_HOME"));

        System.out.println("user.home: " + env.getProperty("user.home"));
        System.out.println("JAVA_HOME: " + env.getProperty("JAVA_HOME"));

        ctx.close();
    }
}
