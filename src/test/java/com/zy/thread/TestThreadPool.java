package com.zy.thread;

import org.junit.Test;

import java.util.concurrent.*;

public class TestThreadPool {

    @Test
    public void testCachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        StringBuffer sb = new StringBuffer();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            final int j = i + 1;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            cachedThreadPool.execute(() -> {
                sb.append("Thread");
                sb.append(j);
                sb.append(" is running. Current thread name is ");
                sb.append(Thread.currentThread().getName());
                sb.append('\n');
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                countDownLatch.countDown();
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
    public void testFixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        StringBuffer sb = new StringBuffer();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            final int j = i + 1;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            fixedThreadPool.execute(() -> {
                sb.append("Thread");
                sb.append(j);
                sb.append(" is running. Current thread name is ");
                sb.append(Thread.currentThread().getName());
                sb.append('\n');
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());
        System.out.println(sb);
    }

    @Test
    public void testScheduledThreadPool() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        StringBuffer sb = new StringBuffer();

        System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());

        long start = System.currentTimeMillis();
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
//                System.out.println("延迟1s，每3s执行一次。");
                sb.append("延迟1s，每3s执行一次。");
                sb.append('\n');
                countDownLatch.countDown();
            }
        }, 1, 3, TimeUnit.SECONDS);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start) / 1000 + "s.");
        System.out.println(sb);
    }

    @Test
    public void testSingleThreadExecutor() {
        ExecutorService  singleThreadExecutor= Executors.newSingleThreadExecutor();
        StringBuffer sb = new StringBuffer();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            final int j = i + 1;

            singleThreadExecutor.execute(() -> {
                sb.append("Thread");
                sb.append(j);
                sb.append(" is running. Current thread name is ");
                sb.append(Thread.currentThread().getName());
                sb.append('\n');
                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());
        System.out.println(sb);
    }
}
