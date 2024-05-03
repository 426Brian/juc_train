package com.juc.jmm;

import com.juc.jmm.entities.DataNumber;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

/**
 * Classname: AccumulatorCompareDemo
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/2-20:46
 * Version: v1.0
 */
public class AccumulatorCompareDemo {
    public static final int threadNum = 50;
    public static final int _1W = 100 * 100;

    public static void main(String[] args) {

//        synchronisedTest();
//        atomicLongTest();
        longAdderTest();
//        longAccumulatorTest();
    }

    public static void synchronisedTest() {
        DataNumber dataNumber = new DataNumber();
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        long start = System.currentTimeMillis();
        for (int i = 1; i <= threadNum; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        dataNumber.addLong();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();

                }
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("synchronize=" + dataNumber.numberLong + "  --- costTime " + (end - start) + "毫秒");
    }

    public static void atomicLongTest() {
        DataNumber dataNumber = new DataNumber();
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        long start = System.currentTimeMillis();
        for (int i = 1; i <= threadNum; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        dataNumber.atomicLongAdd();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();

                }
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("atomicLong=" + dataNumber.atomicLong.get() + "  --- costTime " + (end - start) + "毫秒");
    }

    public static void longAdderTest() {
        DataNumber dataNumber = new DataNumber();
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        long start = System.currentTimeMillis();
        for (int i = 1; i <= threadNum; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        dataNumber.longAdderAdd();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();

                }
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("longAdder=" + dataNumber.longAdder.sum() + "  --- costTime " + (end - start) + "毫秒");
    }

    public static void longAccumulatorTest() {
        DataNumber dataNumber = new DataNumber();
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        long start = System.currentTimeMillis();
        for (int i = 1; i <= threadNum; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 100 * _1W; j++) {
                        dataNumber.longAccumulatorAdd();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();

                }
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("longAccumulator=" + dataNumber.longAccumulator.get() + "  --- costTime " + (end - start) + "毫秒");
    }
}
