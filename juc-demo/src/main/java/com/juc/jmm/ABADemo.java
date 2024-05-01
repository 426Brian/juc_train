package com.juc.jmm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Classname: ABADemo
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/1-21:59
 * Version: v1.0
 */
public class ABADemo {
    static AtomicInteger atomicInteger = new AtomicInteger(100);
    static AtomicStampedReference<Integer> stampReference = new AtomicStampedReference<>(100, 1);

    public static void main(String[] args) {
        abaResovled();
    }

    private static void abaResovled() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 首次流水号：" + stampReference.getStamp());
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            stampReference.compareAndSet(100, 101,
                    1, stampReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " 当前值：" + stampReference.getReference() + " 2次流水号：" + stampReference.getStamp());

            stampReference.compareAndSet(101, 100,
                    stampReference.getStamp(), stampReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " 当前值：" + stampReference.getReference() + " 3次流水号：" + stampReference.getStamp());
            countDownLatch.countDown();
        }, "Thread-A").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 首次流水号：" + stampReference.getStamp());

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            stampReference.compareAndSet(100, 2022,
                    stampReference.getStamp(), stampReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + " 当前值：" + stampReference.getReference() + " 2次流水号：" + stampReference.getStamp());
            countDownLatch.countDown();
        }, "Thread-B").start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " 当前值: " + stampReference.getReference() + "\t 流水号: " + stampReference.getStamp());
    }

    private static void abaHappens() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(() -> {
            atomicInteger.compareAndSet(100, 101);
            atomicInteger.compareAndSet(101, 100);
            countDownLatch.countDown();
        }, "Thread-A").start();

        new Thread(() -> {
            atomicInteger.compareAndSet(100, 2022);
            countDownLatch.countDown();
        }, "Thread-B").start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(atomicInteger.get());
    }

}
