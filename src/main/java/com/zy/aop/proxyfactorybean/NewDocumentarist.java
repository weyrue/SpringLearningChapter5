package com.zy.aop.proxyfactorybean;

import org.springframework.stereotype.Component;

public class NewDocumentarist extends Documentarist {
    @Override
    public void execute() {
        guitarist.sing();
        guitarist.sing("123456");
        guitarist.talk();
    }
}
