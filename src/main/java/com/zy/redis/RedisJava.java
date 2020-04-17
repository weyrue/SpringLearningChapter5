package com.zy.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisJava {
    private static final Logger log = LoggerFactory.getLogger(RedisJava.class);
    private JedisPool jedisPool = null;

    public void init() {
        if (null == jedisPool) {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(8);
            jedisPoolConfig.setMaxIdle(4);
            jedisPoolConfig.setMinIdle(2);
            jedisPoolConfig.setMaxWaitMillis(1000 * 60);
            jedisPoolConfig.setTestOnBorrow(true);
            jedisPoolConfig.setTestWhileIdle(true);
            jedisPool = new JedisPool(jedisPoolConfig, "111.229.151.74", 6379, 1000 * 30, ",Zy401972863");
        }

        Jedis jedis = jedisPool.getResource();
        jedis.connect();
        //查看服务是否运行
        if ("PONG".equals(jedis.ping())) {
            log.info("Redis启动成功，服务正在运行。");
        }
    }

    public void destroy() {
        log.info("Redis服务正在关闭。");
        jedisPool.close();
        log.info("Redis服务关闭成功。");
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }
}
