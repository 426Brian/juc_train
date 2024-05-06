package com.juc.jmm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * Classname: StampLockDemo2
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/6-9:31
 * Version: v1.0
 */
public class StampLockDemo {
    static int number = 30;
    static StampedLock stampedLock = new StampedLock();

    public static void main(String[] args) {
        StampLockDemo resource = new StampLockDemo();
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(() -> {
            System.out.println("读线程 start ------------------");
//            resource.read(); // 悲观读
            resource.tryOptimisticRead();
            countDownLatch.countDown();
        }, "read-thread").start();

        try {
            // 等待两秒，先让获取读锁，读到数据
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            System.out.println("写线程 start ------------------");
            resource.write();
            countDownLatch.countDown();
        }, "write-thread").start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + "线程 获取到最终数据 result=" + number);
    }

    public void read() {
        long stamp = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName() + " ----- 读线程线程获取到【读锁】准备读 ------");
        try {
            for (int i = 1; i <= 4; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " ----- 读线程正在读取 ");
            }
            int result = number;
            System.out.println(Thread.currentThread().getName() + " ----- 读线程读取到的数据 result=" + result);
            System.out.println(Thread.currentThread().getName() + " ----- 读线程读取，写线程无法介入，传统读写锁互斥 ");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            stampedLock.unlockRead(stamp);
        }
    }

    public void write() {
        long stamp = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + " ----- 写线程获取到【写锁】准备修改数据 ------");
        try {
            System.out.println(Thread.currentThread().getName() + " ----- 写线程【修改前】数据 number=" + number);
            number += 20;
            System.out.println(Thread.currentThread().getName() + " ----- 写线程【修改后】数据 number=" + number);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    public void tryOptimisticRead() {
        long stamp = stampedLock.tryOptimisticRead();
        int result = number;
        System.out.println(Thread.currentThread().getName() + " ----- 乐观读线程读取数据 ------result=" + result);
        try {
            for (int i = 1; i <= 4; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    result = number;
                    System.out.println(Thread.currentThread().getName() + " ----- 读线程第【" + i + "】次读取 result=" + result);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName()
                    + " ----- 读线程读取到的数据是否被修改："
                    + (stampedLock.validate(stamp)?"【未被修改】":"【已被修改】"));
            if (!stampedLock.validate(stamp)) {
                System.out.println("number 【被修改】，有写操作");
                long stamp2 = stampedLock.readLock();
                try {
                    System.out.println(Thread.currentThread().getName() + " ----- 从【乐观读】升级为【悲观读】 -----");
                    result = number;
                    System.out.println(Thread.currentThread().getName() + " ----- 【悲观读】，读取到数据 result=" + result);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    stampedLock.unlockRead(stamp2);
                }
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
