package com.zy.component.zookeeper;

import com.zy.zookeeper.ZooKeeperClient;
import com.zy.zookeeper.ZooKeeperLock;
import org.apache.zookeeper.KeeperException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ZooKeeperTest {
    private static final Logger log = LoggerFactory.getLogger(ZooKeeperTest.class);

    @Test
    public void testZooKeeper() throws KeeperException, InterruptedException {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:zookeeper/zookeeper.xml");
//        ac.refresh();

        ZooKeeperClient zkCli = ac.getBean("zk", ZooKeeperClient.class);

        Thread.sleep(500);

        if (!zkCli.isExist("hello")) {
            zkCli.createZNode("hello", "world");
            zkCli.setData("hello", "123");
            log.info(zkCli.getData("hello"));
//            zkCli.getData("hello");
            zkCli.deleteZNode("hello");
        } else {
            zkCli.setData("hello", "123");
            zkCli.deleteZNode("hello");
        }

        ac.registerShutdownHook();
    }

    @Test
    public void testZooKeeperLock() throws IOException, InterruptedException, KeeperException {
        int threadNo = 3;

        ExecutorService executor = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(threadNo);
        ZooKeeperLock lock = new ZooKeeperLock();

        for (int i = 0; i < threadNo; i++) {
            executor.submit(() -> {
                for (int i1 = 0; i1 < 5; i1++) {
                    if (lock.tryLock()) {
                        log.info(Thread.currentThread().getId() + "加锁成功");
                        break;
                    } else {
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                latch.countDown();
            });
        }
        latch.await();

    }
}
