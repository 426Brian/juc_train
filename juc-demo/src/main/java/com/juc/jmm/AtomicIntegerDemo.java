package com.juc.jmm;

import com.juc.jmm.entities.DataNumber;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Classname: AtomicIntegerDemo
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/2-8:10
 * Version: v1.0
 */
public class AtomicIntegerDemo {
    public static final int SIZE = 10;

    public static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[5]);

    public static void main(String[] args) {
        testAtomicInteger();

//        testAtomicIntegerArray();

    }

    private static void testAtomicInteger() {

        int wrongCount = 0;
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " --> ");
            if (testAtomicIntegerBase() != 10000) {
                ++wrongCount;
                System.out.println("\u001B[31m *** wrong result *** \u001B[0m");
            }
        }
        System.out.println("5次测试中，错误次数=" + wrongCount);
    }

    private static void testAtomicIntegerArray() {
        atomicIntegerArray.getAndIncrement(0);
        System.out.println("atomicIntegerArray[0]=" + atomicIntegerArray.get(0));
    }

    private static int testAtomicIntegerBase() {
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);

        DataNumber dataNumber = new DataNumber();
        long start = System.currentTimeMillis();
        for (int i = 1; i <= SIZE; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    dataNumber.atomicAddTest();
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
        System.out.println("number=" + dataNumber.getAtomicInteger());
        System.out.println("--- costTime " + (end - start) + "毫秒");
        return dataNumber.getAtomicInteger().get();
    }
}













