package com.zy.bean.lifeCycle;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestFactory {
    private String algorithmName = "MD5";

    private MessageDigest createInstance() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(algorithmName);
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }
}
