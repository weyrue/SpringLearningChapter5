package com.zy.bean.lifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class LifeCycleBean
        implements InitializingBean, DisposableBean, BeanNameAware, ApplicationContextAware {

    private String beanName;

    private ApplicationContext applicationContext;

    /*
     * ApplicationContextAware接口，可以感知获取ApplicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        System.out.println(applicationContext.getDisplayName());

        if(applicationContext instanceof AbstractApplicationContext){
            ((AbstractApplicationContext) this.applicationContext).registerShutdownHook();
        }
    }

    /*
     * BeanNameAware接口，可以感知获取BeanName
     */
    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        System.out.println("Bean name is " + name);
    }

    public void initMethod() {
        System.out.println("init init-method");
    }

    @PostConstruct
    public void initPostConstruct() {
        System.out.println("init @PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init afterPropertiesSet");
    }

    public void destroyMethod() {
        System.out.println("destroy destroy-method");
    }

    @PreDestroy
    public void destroyPreDestroy() {
        System.out.println("destroy @PreDestroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy destroy");
    }
}
