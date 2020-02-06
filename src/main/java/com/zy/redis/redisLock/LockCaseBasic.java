package com.zy.redis.redisLock;

import redis.clients.jedis.Jedis;

public class LockCaseBasic extends RedisLock {

    @Override
    public void lock() {

        int i = 0;
        while (i++ < 2) {
            Long result = jedis.setnx(lockKey, LockConstants.NOT_EXIST);
            if (LockConstants.NX.equals(result)) {
                System.out.println(Thread.currentThread().getId() + "加锁成功");
                return;
            }
            sleepBySencond(1);
        }
        System.out.println(Thread.currentThread().getId() + "加锁失败");
    }

    @Override
    public void unlock() {
        jedis.del(lockKey);
    }

    public LockCaseBasic(Jedis jedis, String lockKey) {
        super(jedis, lockKey);
    }
}
