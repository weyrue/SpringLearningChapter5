package com.zy.redis;

import com.zy.redis.redisLock.RedisLock;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisTest {
    private static final Logger log = LoggerFactory.getLogger(RedisTest.class);

    @Test
    public void testRedisLock() throws InterruptedException {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring-redis.xml");
        RedisJava redis = (RedisJava) ac.getBean("redisBean");

        int threadNo = 50;

        ExecutorService executorService = Executors.newCachedThreadPool();
        StringBuilder sb = new StringBuilder();

        CountDownLatch countDownLatch = new CountDownLatch(threadNo);

        for (int i = 0; i < threadNo; i++) {
            int j = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        log.debug("Thread" + Thread.currentThread().getId());
                        RedisLock lock = new RedisLock(redis, "lockCaseBasic", 4);
                        lock.lock();
                        sb.append('T');
                        sb.append('h');
                        sb.append('r');
                        sb.append('e');
                        sb.append('a');
                        sb.append('d');
                        sb.append(j);
                        sb.append('\n');
                        lock.unlock();
                        countDownLatch.countDown();
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        countDownLatch.await();
        log.info(sb.toString());

        ac.registerShutdownHook();

    }

}
