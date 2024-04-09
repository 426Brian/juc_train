package com.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: SaleTicket
 * Pacage: com.juc.lock
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/9-22:16
 * Version: V1.0
 */
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 17; i++) {
                ticket.sale();
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 17; i++) {
                ticket.sale();
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 0; i < 17; i++) {
                ticket.sale();
            }
        }, "CC").start();
    }
}

class Ticket {
    static final int COUNT = 51;
    private int number = 50;

    private ReentrantLock lock = new ReentrantLock(true);

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第" + (COUNT - number) + "张票，还剩" + --number + "张票");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
