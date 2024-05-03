package com.juc.jmm;

import com.juc.jmm.entities.DataNumber;

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
        int wrongCount = 0;
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " --> ");
            if (test() != 10000) {
                ++wrongCount;
                System.out.println("\u001B[31m *** wrong result *** \u001B[0m");
            }
        }

        System.out.println("5次测试中，错误次数=" + wrongCount);
    }

    private static int test() {
        int threadNum = 10;
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

