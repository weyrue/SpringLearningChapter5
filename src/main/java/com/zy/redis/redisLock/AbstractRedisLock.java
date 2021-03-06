package com.zy.redis.redisLock;

import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public abstract class AbstractRedisLock implements Lock {
    protected String lockKey;

    public AbstractRedisLock(String lockKey) {
        this.lockKey = lockKey;
    }

    public void sleepBySencond(int sencond) {
        try {
            Thread.sleep(sencond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lock() {
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
