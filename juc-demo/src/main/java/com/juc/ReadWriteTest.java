package com.juc;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ClassName: ReadWriteTest
 * Pacage: com.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/7-20:12
 * Version: V1.0
 */
public class ReadWriteTest {
    public static void main(String[] args) {
        Cache cache = new Cache();
        for (int i = 1; i < 6; i++) {
            final int num = i;
            new Thread(() -> {
                cache.put("" + num, "" + num);
            }, "AA_" + i).start();
        }

        for (int i = 1; i < 6; i++) {
            int finalI = i;
            new Thread(() -> {
                cache.get(String.valueOf(finalI));
            }, "AA_" + i).start();
        }
    }

}

class Cache {
    private volatile HashMap<String, Object> map = new HashMap<String, Object>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();// true 表示公平锁

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + " 正在写 " + key);

        try {
            map.put(key, value);
            TimeUnit.MICROSECONDS.sleep(300);
            System.out.println(Thread.currentThread().getName() + " 写完成 " + key);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public Object get(String key) {
        readWriteLock.readLock().lock();
        Object result;
        System.out.println(Thread.currentThread().getName() + " 正在读 " + key);
        try {
            result = map.get(key);
            TimeUnit.MICROSECONDS.sleep(300);

            System.out.println(Thread.currentThread().getName() + " 读完成 " + key);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            readWriteLock.readLock().unlock();
        }

        return result;
    }
}
