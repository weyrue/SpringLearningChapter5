package com.zy.aop.aspectj;

import com.zy.aop.example.Singer;
import com.zy.aop.pointcut.AdviceRequired;
import org.springframework.stereotype.Component;

@Component("johnMayer")
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
