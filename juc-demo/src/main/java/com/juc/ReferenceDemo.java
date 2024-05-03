package com.juc;

import com.juc.jmm.entities.MyObejct;

import java.lang.ref.SoftReference;

/**
 * Classname: ReferenceDemo
 * Pacage: com.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/3-17:29
 * Version: v1.0
 */
public class ReferenceDemo {
    public static void main(String[] args) {
        SoftReference<Object> objectSoftReference = new SoftReference<>(new MyObejct());
        System.gc();

        System.out.println("gc after 内存够用 " + objectSoftReference.get());

        try {
            byte[] bytes = new byte[20 * 1024 * 1024]; // 20M

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("gc after 内存不够用 " + objectSoftReference.get());

        }
    }

    // 强引用
    private static void strongReference() {
        MyObejct myObejct = new MyObejct();
        System.out.println("gc before " + myObejct);

        myObejct = null;
        System.gc(); // 手动回收
        System.out.println("gc after " + myObejct);
    }
}
