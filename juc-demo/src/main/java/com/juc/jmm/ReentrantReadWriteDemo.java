package com.juc.jmm;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Classname: ReentrantReadWriteDemo
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/5-16:12
 * Version: v1.0
 */
public class ReentrantReadWriteDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource();

        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(()->{
                myResource.write(finalI +"_k2", finalI +"_v2");
            }, "write1_thread-"+String.valueOf(i)).start();
        }
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(()->{
                myResource.read(finalI +"_k" );
            }, "thread-"+String.valueOf(i)).start();
        }

        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            new Thread(()->{
                myResource.write(finalI +"_k", finalI +"_v");
            }, "write2_thread-"+String.valueOf(i)).start();
        }
    }
}

class MyResource {
    HashMap<String, String> map = new HashMap<>();
    Lock lock = new ReentrantLock();

    ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void write(String key, String value) {
        try {
            rwLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " 正在写入 ");
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写入完成 "+" key="+key+", value="+value);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void read(String key) {
        try {
            rwLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " 正在读取 ");
            String result = map.get(key);
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + " 完成读取 "+"key="+key+", value="+result);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            rwLock.readLock().unlock();
        }
    }
}