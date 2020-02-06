package com.zy.redis;

import com.zy.redis.redisLock.LockCaseBasic;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest {

    @Test
    public void testRedisLock(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring-redis.xml");
        RedisJava redisBean =  (RedisJava)ac.getBean("redisBean");

        Jedis jedis = redisBean.getJedisPool().getResource();

        LockCaseBasic lockCaseBasic1 = new LockCaseBasic(jedis,"lockCaseBasic");
        LockCaseBasic lockCaseBasic2 = new LockCaseBasic(jedis,"lockCaseBasic");

        lockCaseBasic1.lock();
        lockCaseBasic2.lock();
        lockCaseBasic1.unlock();
        lockCaseBasic2.lock();
        lockCaseBasic2.unlock();

        jedis.close();

    }

}
