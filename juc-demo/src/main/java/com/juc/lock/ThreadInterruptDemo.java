package com.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: ThreadInterruptDemo
 * Pacage: com.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/10-10:39
 * Version: V1.0
 */
public class ThreadInterruptDemo {

    public static void main(String[] args) {
//        atomic_1();
        atomic_2();
    }

    private static void atomic_1() {
        Thread t_AA = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " isInterrupted 被修改为true，程序停止");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " hello isInterrupted");
            }
        }, "AA");
        t_AA.start();

        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Thread t_BB = new Thread(() -> {
            t_AA.interrupt();
            System.out.println(Thread.currentThread().getName() + " hello isInterrupted");
        }, "BB");
        t_BB.start();
    }

    private static void atomic_2() {
        Thread t_aa = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " isInterrupted 被修改为true，程序停止");
                    break;
                }
                // todo 并未出现像文档中说的，当前线程中的sleep() 方法与其他线程中调该线程interrupt(),会使得中断位清除
                try {
                    Thread.currentThread().sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " hello interrupt");
            }
        }, "thread_AA");

        t_aa.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(() -> {
            t_aa.interrupt();
            System.out.println(Thread.currentThread().getName() + " hello interrupt");
        }, "thread_BB").start();

    }
}
