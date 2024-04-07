package com.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: ThreadTest
 * Pacage: com.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/7-10:09
 * Version: V1.0
 */
public class ThreadTest {
    public static void main(String[] args) {
        Share3 share3 = new Share3();
        new Thread(() -> {
            share3.print5();
        }, "AA").start();
        new Thread(() -> {
            share3.print10();
        }, "BB").start();
        new Thread(() -> {
            share3.print15();
        }, "CC").start();

    }
}

class Share3 {
    // 1: AA 2: BB 3: CC
    private int flag = 1;
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() {
        lock.lock();

        try {
            while (flag != 1) {
                condition1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i);
            }
            flag = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();

        try {
            while (flag != 2) {
                condition2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i);
            }
            flag = 3;
            condition2.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();

        try {
            while (flag != 3) {
                condition3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i);
            }
            flag = 1;
            condition3.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}


