package com.juc.jmm;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * Classname: LongAdderDemo
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/2-20:18
 * Version: v1.0
 */
public class LongAdderDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();

        longAdder.increment();
        longAdder.increment();
        longAdder.increment();

        System.out.println("longAdder=" + longAdder.sum());

        /*
        累加器，两个参数的函数式接口（x, y）返回对x 和 Y 的操作，比如加减乘除
        给定x 的默认值，在接下来的方法调用中传入Y 的值，如下。
        */
        LongAccumulator longAccumulator = new LongAccumulator((x, y) -> {
            return x + y; // 加减乘除都可以
        }, 0);
        longAccumulator.accumulate(5);
        longAccumulator.accumulate(3);
        System.out.println("longAccumulator=" + longAccumulator.get());

    }
}
