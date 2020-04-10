package com.zy.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    @Test
    public void testReentrantLock() {
        StringBuilder sb = new StringBuilder();
        final ReentrantLock reentrantLock = new ReentrantLock();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            int j = i + 1;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        reentrantLock.lock();
                        sb.append("thread");
                        sb.append(j);
                        sb.append(' ');
                        sb.append("get");
                        sb.append(' ');
                        sb.append("lock");
                        sb.append('\n');
                    } finally {
                        countDownLatch.countDown();
                        reentrantLock.unlock();
                    }
                }
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sb);
    }

    @Test
    public void testCondition() {
        Integer a, b;

        a = new Integer(1);
        b = new Integer(1);
        System.out.println("都是new出来的a==b：" + (a == b));

        a = Integer.valueOf(1);
        b = Integer.valueOf(1);
        System.out.println("不是new出来的a==b（-128~127）：" + (a == b));

        a = Integer.valueOf(200);
        b = Integer.valueOf(200);
        System.out.println("不是new出来的a==b：" + (a == b));

        a = Integer.valueOf(200);
        int c = 200;
        System.out.println("Integer和int比较a==b：" + (a == c));

        String s1 = new String("abc")+new String("abc");
        String s2 = new String("abc");

        System.out.println("s1 == s2:" + (s1 == s2));
        System.out.println("s1.intern()==s1:" + (s1.intern() == s1));
        System.out.println("s2.intern()==s2:" + (s2.intern() == s2));

    }

}
