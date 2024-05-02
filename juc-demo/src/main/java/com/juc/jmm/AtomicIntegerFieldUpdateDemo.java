package com.juc.jmm;

import com.juc.jmm.entities.BankAccount;

import java.util.concurrent.CountDownLatch;

/**
 * Classname: AtomicIntegerFieldUpdateDemo
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/2-11:08
 * Version: v1.0
 */
public class AtomicIntegerFieldUpdateDemo {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 1000; j++) {
                        bankAccount.add();
                    }
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

        System.out.println(Thread.currentThread().getName()+" money="+bankAccount.getMoney());
    }
}
