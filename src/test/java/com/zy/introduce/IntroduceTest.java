package com.zy.introduce;

import com.zy.proxy.ForumService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IntroduceTest {
    @Test
    public void intruduceTest(){
        String configPath = "spring/spring-aop.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
        ForumService forumService = (ForumService) ctx.getBean("forumService2");

        forumService.removeForum(10);
        forumService.removeTopic(1022);

        Monitorable monitorable = (Monitorable)forumService;
        monitorable.setMonitorActive(true);

        forumService.removeForum(10);
        forumService.removeTopic(1022);
    }
}
