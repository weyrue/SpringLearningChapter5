package com.zy.zookeeper;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ZooKeeperLock implements Lock {
    private static final Logger log = LoggerFactory.getLogger(ZooKeeperLock.class);
    private final String connectString = "111.229.151.74:2181";
    private final int sessionTimeout = 10000;
    private ZooKeeper zk = null;
    private String path;

    public ZooKeeperLock() throws IOException, InterruptedException, KeeperException {
        init();
        if (zk.exists(path, false) == null) {
                zk.create(path,null,ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }
    }

    /**
     * 初始化
     *
     * @author Yi
     * @since 4/27/2020
     */
    public void init() throws IOException, InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    latch.countDown();
                }
            }
        });

        latch.await();
        path = "/" + UUID.randomUUID().toString();
    }

    public void close() throws InterruptedException {
        if (zk != null) {
            zk.close();
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
        try {
            List<String> children = zk.getChildren(path, false);
            // 有线程获得锁时，检查当前线程是否获得锁
            if (children != null && !children.isEmpty()){
                log.info(children.toString());
                return String.valueOf(Thread.currentThread().getId()).equals(children.get(0));
            }
            // 没有线程获得锁时
            zk.create(path + '/' + Thread.currentThread().getId(), null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
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
