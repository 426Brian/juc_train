package com.juc.jmm.entities;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Classname: DataNumber
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/2-8:10
 * Version: v1.0
 */
@Data
public class DataNumber {
    public volatile int number;

    public AtomicInteger atomicInteger = new AtomicInteger();

    public synchronized void add() {
        ++number;
    }

    public void atomicAddTest() {
        atomicInteger.getAndIncrement();
    }
}
