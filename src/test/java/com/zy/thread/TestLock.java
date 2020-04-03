package com.zy.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
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
        StringBuilder sb = new StringBuilder();
        final ReentrantLock reentrantLock = new ReentrantLock();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        ExecutorService executorService = Executors.newCachedThreadPool();

        Condition condition = reentrantLock.newCondition();

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
                        condition.await();
                        sb.append("get");
                        sb.append(' ');
                        sb.append("lock");
                        sb.append('\n');
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        countDownLatch.countDown();
                        reentrantLock.unlock();
                    }
                }
            });
        }

        try {
            reentrantLock.lock();
            condition.notifyAll();
        } finally {
            reentrantLock.unlock();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sb);
    }

}
