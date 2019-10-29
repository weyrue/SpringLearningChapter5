package com.zy.beanFactory;

import com.zy.injectFun.MagicFun;
import com.zy.reflect.Car;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.text.MessageFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class BeanFactoryTest {
    @Test
    public void getbean() throws Throwable {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:spring/spring-context.xml");

        System.out.println(resource.getURL());

        //XmlBeanDefinitionReader通过Resource装载spring配置信息并启动IOC容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);

        System.out.println("init beanfactory");

        Car car1 = factory.getBean("car1", Car.class);
        Car car = factory.getBean("car", Car.class);
        car.introduce();

        Locale locale = Locale.PRC;
    }

    @Test
    public void singletonGetPrototype() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
        MagicFun magicBoss = (MagicFun) ac.getBean("magicBoss");

        System.out.println(magicBoss.getCar());
        System.out.println(magicBoss.getCar());
        System.out.println(magicBoss.getCar());

        ((Car) ac.getBean("car2")).introduce();
        ((Car) ac.getBean("car3")).introduce();

    }

    @Test
    public void testLocale() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
        String pattern1 = "{0}，您好！您于{1}在工商银行存入{2}元";
        String pattern2 = "At {1,time,short} On {1,date,long}, {0} paid {2,number,currency}.";

        Object[] params = {"John", new GregorianCalendar().getTime(), 1.0E3};

        String msg1 = MessageFormat.format(pattern1, params);

        MessageFormat mf = new MessageFormat(pattern2, Locale.US);
        String msg2 = mf.format(params);
        System.out.println(msg1);
        System.out.println(msg2);

        String str1 = ac.getMessage("greeting.common", params, Locale.getDefault());
//        String str2 = ac.getMessage("greeting.morning", params, Locale.US);
        System.out.println(str1);
//        System.out.println(str2);

    }
}
