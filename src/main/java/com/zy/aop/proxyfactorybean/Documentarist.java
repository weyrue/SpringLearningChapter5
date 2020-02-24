package com.zy.aop.proxyfactorybean;

import com.zy.aop.pointcut.GoodGuitarist;

public class Documentarist {
    protected GoodGuitarist guitarist;

    public void execute() {
        guitarist.sing();
        guitarist.talk();
    }

    public void setGuitarist(GoodGuitarist guitarist) {
        this.guitarist = guitarist;
    }
}
