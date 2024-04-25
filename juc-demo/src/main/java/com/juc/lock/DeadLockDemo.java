package com.juc.lock;

/**
 * ClassName: DeadLockDemo
 * Pacage: com.juc.lock
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/10-10:05
 * Version: V1.0
 */
public class DeadLockDemo {
    static final Object object1 = new Object();
    static final Object object2 = new Object();

    public static void main(String[] args) {
        deadLock();
    }

    private static void deadLock() {
        new Thread(() -> {
            synchronized (object1) {
                System.out.println(Thread.currentThread().getName() + " object1");
                synchronized (object2) {

                }
            }
        }, "AA").start();
        new Thread(() -> {
            synchronized (object2) {
                System.out.println(Thread.currentThread().getName() + " object1");
                synchronized (object1) {

                }
            }
        }, "BB").start();
    }
}
