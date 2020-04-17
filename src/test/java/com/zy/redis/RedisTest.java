package com.zy.redis;

import com.zy.redis.redisLock.LockCaseBasic;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisTest {
    private static final Logger log = LoggerFactory.getLogger(RedisTest.class);

    @Test
    public void testRedisLock() throws InterruptedException {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring-redis.xml");
        RedisJava redisBean = (RedisJava) ac.getBean("redisBean");

        ExecutorService executorService = Executors.newCachedThreadPool();
        StringBuilder sb = new StringBuilder();
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 10; i++) {
            int j = i;
            executorService.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
//                    Jedis jedis = null;
                    try {
                        log.info("Thread"+Thread.currentThread().getId());
//                        jedis = redisBean.getJedisPool().getResource();
                        LockCaseBasic lock = new LockCaseBasic(redisBean.getJedisPool().getResource(), "lockCaseBasic", 4);
                        lock.lock();
                        sb.append('T');
                        sb.append('h');
                        sb.append('r');
                        sb.append('e');
                        Thread.sleep(20);
                        sb.append('a');
                        sb.append('d');
                        sb.append(j);
                        sb.append('\n');
                        lock.unlock();
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });
        }

        countDownLatch.await();
        log.info(sb.toString());

        ac.registerShutdownHook();

    }

}
