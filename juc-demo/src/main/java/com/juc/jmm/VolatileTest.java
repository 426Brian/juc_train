package com.juc.jmm;

import java.util.concurrent.TimeUnit;

/**
 * Classname: VolatileTest
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/1-11:15
 * Version: v1.0
 */
public class VolatileTest {
    static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in");
            while (flag) {
            }
            System.out.println("flag 被更新为false "+Thread.currentThread().getName()+" 结束");
        }, "thread-A").start();

        try {
            // 延迟主线程更新 flag 的 时机
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        flag  = false;

        System.out.println(Thread.currentThread().getName()+" 结束");
    }
}
