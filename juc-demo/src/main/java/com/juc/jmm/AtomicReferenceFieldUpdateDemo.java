package com.juc.jmm;

import com.juc.jmm.entities.BankAccount;

import java.util.concurrent.CountDownLatch;

/**
 * Classname: AtomicReferenceFieldUpdateDemo
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/2-15:44
 * Version: v1.0
 */
public class AtomicReferenceFieldUpdateDemo {
    public static void main(String[] args) {
        test();

    }

    private static void test() {
        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                bankAccount.flagChange(bankAccount);
                countDownLatch.countDown();

            }, "thread-" + String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
