package com.juc.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * Classname: NoAtomic
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/1-11:28
 * Version: v1.0
 */
public class NoAtomic {
    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            System.out.print(i + " --> ");
            if (test() != 50000) {
                System.out.println("\u001B[31m *** wrong result *** \u001B[0m");
            }
        }
    }

    private static int test() {
        int threadNum = 50;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        DataNumber dataNumber = new DataNumber();

        long start = System.currentTimeMillis();
        for (int i = 1; i <= threadNum; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    dataNumber.add();
                }
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("number=" + dataNumber.number);
        System.out.println("--- costTime " + (end - start) + "毫秒");
        return dataNumber.number;
    }

}

class DataNumber {
    volatile int number;

    public synchronized void add() {
        ++number;
    }
}