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
//                        bankAccount.add();
                        bankAccount.transferMoney(bankAccount);
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

        if (bankAccount.getMoney() != 10000) {
            System.out.println("predicate money=10000;  -->"+"\u001B[31m *** wrong result *** \u001B[0m" +" money="+ bankAccount.getMoney()+"\t");
        } else {
            System.out.println("predicate money=10000;  -->"+ " result money=" + bankAccount.getMoney()+"\t");

        }
    }
}
