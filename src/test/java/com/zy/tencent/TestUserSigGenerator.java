package com.zy.tencent;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestUserSigGenerator {
    @Test
    public void testUserSigGenerator() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:tencent/user-sig-generator.xml");
        ctx.refresh();

        UserSigGenerator userSigGenerator = ctx.getBean("userSigGenerator", UserSigGenerator.class);
        System.out.println(userSigGenerator.genSig("administrator", 604800));

        ctx.close();
    }

    @Test
    public void testRestful(){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:tencent/user-sig-generator.xml");
        ctx.refresh();

        RestfulAction restfulAction = ctx.getBean("restfulAction", RestfulAction.class);
        restfulAction.restTem();

        ctx.close();
    }
}
