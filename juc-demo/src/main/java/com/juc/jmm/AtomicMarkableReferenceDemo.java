package com.juc.jmm;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * Classname: AtomicMarkableReferenceDemo
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/2-9:35
 * Version: v1.0
 */
public class AtomicMarkableReferenceDemo {
    static AtomicMarkableReference<Integer> markableReference = new AtomicMarkableReference<>(100, false);

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + " 默认标识 " + marked);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            boolean result = markableReference.compareAndSet(100, 1000, marked, !marked);

            System.out.println(Thread.currentThread().getName()
                    + " 更新值 " + (result == true ? "成功 " : "失败 ")
                    + "当前值 "+markableReference.getReference() + "\t"
                    + markableReference.isMarked());
            countDownLatch.countDown();
        }, "t-1").start();

        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + " 默认标识 " + marked);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean result = markableReference.compareAndSet(100, 1000, marked, !marked);
            System.out.println(Thread.currentThread().getName()
                    + " 更新值 " + (result == true ? "成功 " : "失败 ")
                    + "当前值 "+markableReference.getReference() + "\t"
                    + markableReference.isMarked());
            countDownLatch.countDown();
        }, "t-2").start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " 当前值" + markableReference.getReference() + "\t" + markableReference.isMarked());
    }
}
