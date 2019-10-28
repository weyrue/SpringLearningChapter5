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
}
