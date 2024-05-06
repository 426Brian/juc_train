package com.juc.jmm;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Classname: AQSDemo
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/5-8:13
 * Version: v1.0
 */
public class AQSDemo {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        System.out.println(Integer.valueOf(0x80000000));
    }
}
