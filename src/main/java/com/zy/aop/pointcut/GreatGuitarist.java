package com.zy.aop.pointcut;

import com.zy.aop.example.Singer;

public class GreatGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("I'm a great guitarist.");
    }
}
