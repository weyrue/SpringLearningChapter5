package com.zy.aop.aspectj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("documentarist")
public class NewDocumentarist {
    @Autowired
    private GoodGuitarist guitarist;

    public void execute() {
        guitarist.sing();
        guitarist.sing("123456");
        guitarist.talk();
    }
}
