package com.bryan.test.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: SaleTicket
 * Pacage: com.bryan.test.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/6-18:45
 * Version: V1.0
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.saleLock();
            }
        }, "Thread1").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.saleLock();
            }
        }, "Thread2").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.saleLock();
            }
        }, "Thread3").start();
    }

    public static void test(Ticket ticket) {
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "Thread1").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "Thread2").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale();
            }
        }, "Thread3").start();
    }
}
class Ticket {
    private final ReentrantLock lock = new ReentrantLock();
    private int number = 30;

    // 监视器同步方法
    public synchronized void sale() {

        if (number > 0) {
            number--;
            System.out.println(Thread.currentThread().getName() + "卖出第" + (30 - number) + "还剩" + number + "张票");
        }
    }

    public void saleLock() {

        //上锁
        lock.lock();
        try {
            if (number > 0) {
                number--;
                System.out.println(Thread.currentThread().getName() + "卖出第" +(30 - number)+ "还剩" + number + "张票");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 释放锁
            lock.unlock();
        }

    }
}
