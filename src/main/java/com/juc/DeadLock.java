package com.juc;

/**
 * ClassName: DeadLock
 * Pacage: com.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/7-12:30
 * Version: V1.0
 */
public class DeadLock {
    public static void main(String[] args) {
        Object obj_A = new Object();
        Object obj_B = new Object();

        new Thread(() -> {
            synchronized (obj_A) {
                System.out.println(Thread.currentThread().getName() + "  持有obj_A, 试图获取obj_B");

                synchronized (obj_B) {
                    System.out.println(Thread.currentThread().getName() + "  获取obj_B");
                }

            }
        }, "AA").start();
        new Thread(() -> {
            synchronized (obj_B) {
                System.out.println(Thread.currentThread().getName() + "  持有obj_B, 试图获取obj_A");

                synchronized (obj_A) {
                    System.out.println(Thread.currentThread().getName() + "  获取obj_A");
                }

            }
        }, "BB").start();
    }
}
