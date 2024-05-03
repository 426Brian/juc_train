package com.juc.jmm.entities;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

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
    public volatile long numberLong;
    public AtomicInteger atomicInteger = new AtomicInteger();

    public AtomicLong atomicLong = new AtomicLong(0);
    public LongAdder longAdder = new LongAdder();
    public LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 0);

    public synchronized void add() {
        ++number;
    }

    public synchronized void addLong() {
        ++numberLong;
    }


    public void atomicAddTest() {
        atomicInteger.getAndIncrement();
    }

    public void atomicLongAdd() {
        atomicLong.getAndIncrement();
    }

    public void longAccumulatorAdd() {
        longAccumulator.accumulate(1);
    }

    public void longAdderAdd() {
        longAdder.increment();
    }

}
