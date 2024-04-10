package com.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * ClassName: InterruptDemo
 * Pacage: com.juc.lock
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/10-10:24
 * Version: V1.0
 */
public class InterruptDemo {
    static volatile boolean isStop = false;
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
//        noAtomic();
        atomic();
    }

    private static void noAtomic() {
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + " isStop 被修改为true，程序停止");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " hello volatile");
            }
        }, "AA").start();

        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(() -> {
            isStop = true;
            System.out.println(Thread.currentThread().getName() + " hello volatile");
        }, "BB").start();
    }

    private static void atomic() {
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + " isStop 被修改为true，程序停止");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " hello atomicBoolean");
            }
        }, "AA").start();

        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(() -> {
            atomicBoolean.set(true);
            System.out.println(Thread.currentThread().getName() + " hello atomicBoolean");
        }, "BB").start();
    }
}
