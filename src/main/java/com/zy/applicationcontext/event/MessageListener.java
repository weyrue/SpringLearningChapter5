package com.zy.applicationcontext.event;

import org.springframework.context.ApplicationListener;

public class MessageListener implements ApplicationListener<MessageEvent> {
    @Override
    public void onApplicationEvent(MessageEvent messageEvent) {
        System.out.println(messageEvent.getMsg());
    }
}
