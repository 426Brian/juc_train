package com.juc.jmm;

import lombok.Data;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Classname: ThreadLocalDemo
 * Pacage: com.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/3-12:36
 * Version: v1.0
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
//        threadLocalTest();
        ExecutorService threadPool = Executors.newFixedThreadPool(30);
        try {
            for (int i = 1; i <= 10000; i++) {
                threadPool.submit(() -> {
                    try {
                        Integer before_count = MyNumber.threadCount.get();
                        MyNumber.threadCountAdd();

                        Integer after_count = MyNumber.threadCount.get();
                        System.out.println(Thread.currentThread().getName() + " before_count = " + before_count + "  after_count = " + after_count);
                    } finally {
                        MyNumber.threadCount.remove();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void threadLocalTest() {
        MyNumber myNumber = new MyNumber();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 1000; j++) {
                        myNumber.add();
                        myNumber.threadCountAdd();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + " threadCount=" + myNumber.threadCount.get());
                    myNumber.threadCount.remove();
                    countDownLatch.countDown();
                }
            }, "thread-" + String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main 线程 result=" + myNumber.getNumber());
    }
}

@Data
class MyNumber {
    int number;
    /*   ThreadLocal<Integer> threadCount = new ThreadLocal<>(){
           @Override
           protected Integer initialValue() {
               return 0;
           }
       };
   */
   static ThreadLocal<Integer> threadCount = ThreadLocal.withInitial(() -> 0);

    public static void threadCountAdd() {
        threadCount.set(1 + threadCount.get());
    }

    public synchronized void add() {
        number++;
    }
}
