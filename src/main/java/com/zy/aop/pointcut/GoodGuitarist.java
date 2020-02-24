package com.zy.aop.pointcut;

import com.zy.aop.example.Singer;
import org.springframework.stereotype.Component;

public class GoodGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("I'm a good guitarist.");
    }

    public void sing(String lyric) {
        System.out.println(lyric);
        int a = 1;
    }

    @AdviceRequired
    public void talk() {
        System.out.println("talk");
    }

    public void rest() {
        System.out.println("zzZ");
    }
}
