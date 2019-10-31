package com.zy.aspectj.annotion;

import org.junit.Test;

import java.lang.reflect.Method;

public class ToolTest {
    @Test
    public void tool() {
        Class clazz = ForumService.class;
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            NeedTest nt = method.getAnnotation(NeedTest.class);
            if (nt != null) {
                System.out.println(method.getName() + "()" + (nt.value() ? "" : "不") + "需要测试");
            }
        }
    }
}
