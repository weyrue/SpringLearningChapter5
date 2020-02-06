package com.zy.bean.lifeCycle;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.security.MessageDigest;

public class MessageDigestFactoryBean
        implements FactoryBean<MessageDigest>, InitializingBean {
    private String algorithmName = "MD5";
    private MessageDigest messageDigest = null;

    @Override
    public MessageDigest getObject() throws Exception {
//        return messageDigest = MessageDigest.getInstance(algorithmName);
        return messageDigest;
    }

    @Override
    public Class<?> getObjectType() {
        return MessageDigest.class;
    }

    // 返回的是FactoryBean是否是单例
    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        messageDigest = MessageDigest.getInstance(algorithmName);
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }
}
