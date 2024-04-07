package com.juc;

import java.util.concurrent.CountDownLatch;

/**
 * ClassName: CountDownLatchDemo
 * Pacage: com.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/7-14:47
 * Version: V1.0
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 号同学离开");
                countDownLatch.countDown();
            }, "A_" + i).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() +"班长锁门走人");
    }
}
