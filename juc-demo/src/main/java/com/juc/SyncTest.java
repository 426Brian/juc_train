package com.juc;

/**
 * ClassName: SyncTest
 * Pacage: com.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/7-9:19
 * Version: V1.0
 */
public class SyncTest {
    public static void main(String[] args) {
        share share = new share();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.increace();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decreace();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.increace();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "CC").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decreace();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "DD").start();
    }
}

class share {
    private int number = 0;

    public synchronized void increace() throws InterruptedException {
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "::" + number);

        this.notifyAll();
    }

    public synchronized void decreace() throws InterruptedException {
        while (number != 1) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "::" + number);

        this.notifyAll();
    }
}