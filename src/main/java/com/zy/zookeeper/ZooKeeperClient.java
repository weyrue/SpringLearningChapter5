package com.zy.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@SuppressWarnings("unused")
public class ZooKeeperClient {
    private static final Logger log = LoggerFactory.getLogger(ZooKeeperClient.class);
    private final String connectString = "111.229.151.74:2181";
    private final int sessionTimeout = 500;
    private ZooKeeper zk = null;

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
//                log.info(watchedEvent.getPath() + "\t" + watchedEvent.getState() + "\t" + watchedEvent.getType());
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    latch.countDown();
                }
            }
        });

        latch.await();
        log.info("ZooKeeper创建成功");
    }

    public void destroy() throws InterruptedException {
        if (zk != null) {
            zk.close();
        }
    }

    public String createZNode(String name, String data) throws KeeperException, InterruptedException {
        return zk.create("/" + name,
                data.getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
    }

    public List<String> getChildren(String node) throws KeeperException, InterruptedException {
        return zk.getChildren("/" + node, true);
    }

    public void deleteZNode(String name) throws KeeperException, InterruptedException {
        zk.delete("/" + name, -1);
    }

    public boolean isExist(String name) throws KeeperException, InterruptedException {
        return null != zk.exists("/" + name, false);
    }

    public Stat setData(String name, String data) throws KeeperException, InterruptedException {
        return zk.setData("/" + name, data.getBytes(StandardCharsets.UTF_8), -1);
    }

    public String getData(String name) throws KeeperException, InterruptedException {
        byte[] data = zk.getData("/" + name, false, null);
        return new String(data, StandardCharsets.UTF_8);
    }
}
