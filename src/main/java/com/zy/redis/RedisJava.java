package com.zy.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisJava {
    private JedisPool jedisPool = null;

    public void init() {
        if (null == jedisPool) {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(32);
            jedisPoolConfig.setMaxWaitMillis(1000 * 60);
            jedisPoolConfig.setTestOnBorrow(true);
            jedisPool = new JedisPool(jedisPoolConfig, "11.8.219.36", 6379, 1000 * 30, "aliOS123");
        }

        Jedis jedis = jedisPool.getResource();
        jedis.connect();
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
        jedis.close();
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

}
