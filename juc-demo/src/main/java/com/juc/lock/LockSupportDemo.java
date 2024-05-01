package com.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * ClassName: LockSupportDemo
 * Pacage: com.juc.lock
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/10-11:48
 * Version: V1.0
 */
public class LockSupportDemo {
    public static void main(String[] args) {
        Thread thread_A = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in "+System.currentTimeMillis());
         /*   try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + " 被唤醒 "+System.currentTimeMillis());
        }, "AA");

        Thread thread_B = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 发出通知 "+System.currentTimeMillis());
            LockSupport.unpark(thread_A);
        }, "BB");

        thread_B.start();

        // todo 开启等待执行结果不同
    /*    try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        thread_A.start();

    }

}
