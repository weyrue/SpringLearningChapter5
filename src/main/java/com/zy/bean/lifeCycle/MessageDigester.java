package com.zy.bean.lifeCycle;

import java.security.MessageDigest;

public class MessageDigester {
    private MessageDigest messageDigest1;
    private MessageDigest messageDigest2;

    public void setMessageDigest1(MessageDigest messageDigest1) {
        this.messageDigest1 = messageDigest1;
    }

    public void setMessageDigest2(MessageDigest messageDigest2) {
        this.messageDigest2 = messageDigest2;
    }

    public void digest(String msg) {
        System.out.println("Using digest1");
        digest(msg, messageDigest1);

        System.out.println("Using digest2");
        digest(msg, messageDigest2);
    }

    private void digest(String msg, MessageDigest digest) {
        System.out.println("Using algorithm: " + digest.getAlgorithm());
        digest.reset();
        byte[] bytes = msg.getBytes();
        byte[] out = digest.digest(bytes);
        System.out.println(out);
    }
}
