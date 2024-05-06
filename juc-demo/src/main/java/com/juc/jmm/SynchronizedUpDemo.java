package com.juc.jmm;

import lombok.Data;
import org.openjdk.jol.info.ClassLayout;

/**
 * Classname: SynchronizedUpDemo
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/4-14:47
 * Version: v1.0
 */
@Data
public class SynchronizedUpDemo {
    private static final int COUNT = 51;
    private static int countNum = 50;

    public static void main(String[] args) {
//        objectHeader();
        new Thread(() -> {
            for (int i = 1; i <= 30; i++) {
                SynchronizedUpDemo.sale();
            }
        }, "thread-A").start();
        new Thread(() -> {
            for (int i = 1; i <= 30; i++) {
                SynchronizedUpDemo.sale();
            }
        }, "thread-B").start();
        new Thread(() -> {
            for (int i = 1; i <= 30; i++) {
                SynchronizedUpDemo.sale();
            }
        }, "thread-C").start();
        System.out.println(ClassLayout.parseInstance(SynchronizedUpDemo.class).toPrintable());
    }

    private static void objectHeader() {
        Object object = new Object();
        System.out.println("object 10进制 hashCode=" + object.hashCode());
        System.out.println("object 16进制 hashCode=" + Integer.toHexString(object.hashCode()));
        System.out.println("object 2进制 hashCode=" + Integer.toBinaryString(object.hashCode()));

    }

    public static synchronized void sale() {
        if (countNum > 0) {
            System.out.println(Thread.currentThread().getName() + " \t卖出第" + (COUNT - countNum) + "张票, 还剩" + (--countNum) + "张票");
        }
    }

}
