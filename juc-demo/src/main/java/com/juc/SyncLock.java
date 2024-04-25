package com.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: SyncLock
 * Pacage: com.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/7-12:09
 * Version: V1.0
 */
public class SyncLock {
    public static void main(String[] args) {
        lockTest();

//        SyncLock.add();
//        syncTest();
    }

    private static void lockTest() {
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "外层");

                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "内层");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        }, "LOCK_test").start();
        new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName());
            lock.unlock();

        }, "LOCK_testB").start();
    }

    private static void syncTest() {
        Object obj = new Object();

        new Thread(() -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + "外层");

                synchronized (obj) {
                    System.out.println(Thread.currentThread().getName() + "中层");

                    synchronized (obj) {
                        System.out.println(Thread.currentThread().getName() + "内层");
                    }

                }

            }
        }, "AA").start();
    }

    public synchronized static void add() {
        // 递归证明synchronized 可重入锁
        add();
    }
}
