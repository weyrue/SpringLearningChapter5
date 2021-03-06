package com.zy.redis.redisLock;

import com.zy.redis.RedisJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.params.SetParams;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RedisLock extends AbstractRedisLock {
    private static final Logger log = LoggerFactory.getLogger(RedisLock.class);
    private RedisJava redis;
    private ScheduledExecutorService executorService;
    private String lockValue;
    private int expireTimeInSeconds;
    private SetParams setParams;
    private boolean haveKey = false;

    public RedisLock(RedisJava redis, String lockKey, int expireTimeInSeconds) {
        super(lockKey);
        this.redis = redis;
        setParams = new SetParams();
        setParams.nx();
        setParams.ex(expireTimeInSeconds);
        this.expireTimeInSeconds = expireTimeInSeconds;
    }

    @Override
    public void lock() throws RuntimeException {
        if (reentryLock()) {
            log.debug("线程" + Thread.currentThread().getId() + ":已获得锁");
            return;
        }

        int i = 0;
        while (i++ < 30) {
            lockValue = UUID.randomUUID().toString();
            String result = redis.set(lockKey, lockValue, setParams);

            if (LockConstants.OK.equals(result)) {
                haveKey = true;
                executorService = new ScheduledThreadPoolExecutor(1);
                executorService.scheduleAtFixedRate(new renewLock(), expireTimeInSeconds / 3, expireTimeInSeconds / 2, TimeUnit.SECONDS);
                log.debug("线程" + Thread.currentThread().getId() + ":加锁成功");
                return;
            }
            sleepBySencond(1);
        }
        log.debug("线程" + Thread.currentThread().getId() + ":加锁失败");
        throw new RuntimeException("加锁失败");
    }

    public boolean reentryLock() {
        if (lockValue == null) return false;
        String remoteLockValue = redis.get(lockKey);
        return lockValue.equals(remoteLockValue);
    }

    @Override
    public void unlock() {
        if (reentryLock()) {
            haveKey = false;

            String script = "if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('del',KEYS[1]) else return '0' end";
            String result = redis.eval(script, Arrays.asList(lockKey), Arrays.asList(lockValue)).toString();

            if ("0".equals(result)) {//解锁失败
//                haveKey = true;
                throw new RuntimeException("解锁失败");
            } else {//解锁成功
                while (!executorService.shutdownNow().isEmpty()) {
                }
                log.debug("线程" + Thread.currentThread().getId() + ":解锁成功");
            }
        }
    }

    class renewLock implements Runnable {
        @Override
        public void run() {
            if (haveKey) {
                String result = redis.set(lockKey, lockValue, setParams);
                if (LockConstants.OK.equals(result)) {
                    log.debug("线程" + Thread.currentThread().getId() + ":续锁成功");
                }
            }
        }
    }
}
