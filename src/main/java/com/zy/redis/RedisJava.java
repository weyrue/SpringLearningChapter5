package com.zy.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.SetParams;

import java.util.List;

public class RedisJava {
    private static final Logger log = LoggerFactory.getLogger(RedisJava.class);
    private JedisPool jedisPool = null;

    public Object eval(String script, List<String> keys, List<String> args) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.eval(script, keys, args);
        } finally {
            if (jedis != null) jedis.close();
        }
    }

    public String set(String key, String value, SetParams params) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value, params);
        } finally {
            if (jedis != null) jedis.close();
        }
    }

    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Object value = jedis.get(key);

            return value == null ? null : value.toString();
        } finally {
            if (jedis != null) jedis.close();
        }
    }

    public void init() {
        if (null == jedisPool) {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(20);
            jedisPoolConfig.setMaxIdle(4);
            jedisPoolConfig.setMinIdle(2);
            jedisPoolConfig.setMaxWaitMillis(1000 * 60);
            jedisPoolConfig.setTestOnBorrow(true);
            jedisPoolConfig.setTestWhileIdle(true);
            jedisPool = new JedisPool(jedisPoolConfig, "111.229.151.74", 6379, 1000 * 30, ",Zy401972863");
        }

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.connect();
            //查看服务是否运行
            if ("PONG".equals(jedis.ping())) {
                log.info("Redis启动成功，服务正在运行。");
            }
        } finally {
            if (jedis != null) jedis.close();
        }
    }

    public void destroy() {
        log.info("Redis服务正在关闭。");
        jedisPool.close();
        log.info("Redis服务关闭成功。");
    }

}
