package com.juc;

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
        SyncLock.add();
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

    public synchronized static void add(){
        // 递归证明synchronized 可重入锁
        add();
    }
}
