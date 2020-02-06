package com.zy.redis.redisLock;

public class LockConstants {
    public static final Long NX = 1L;

    /**
     * NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key if it already exist.
     **/
    public static final String NOT_EXIST = "NX";
    public static final String EXIST = "XX";

    /**
     * expx EX|PX, expire time units: EX = seconds; PX = milliseconds
     **/
    public static final String SECONDS = "EX";
    public static final String MILLISECONDS = "PX";

    private LockConstants() {
    }
}
