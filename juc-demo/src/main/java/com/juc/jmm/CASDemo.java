package com.juc.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Classname: CASDemo
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/1-16:01
 * Version: v1.0
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2022) + "\t" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2024) + "\t" + atomicInteger.get());
    }
}
