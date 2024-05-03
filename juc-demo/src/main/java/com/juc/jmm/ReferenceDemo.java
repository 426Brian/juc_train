package com.juc.jmm;

import com.juc.jmm.entities.MyObejct;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        MyObejct myObejct = new MyObejct();
        ReferenceQueue<MyObejct> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObejct> phantomReference = new PhantomReference<>(myObejct, referenceQueue);
        List<byte[]> list = new ArrayList<>();

        new Thread(() -> {
            while (true) {
                list.add(new byte[1024 * 1024]);
                System.out.println("list add ok");
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(phantomReference.get());
            }
        }, "thread-1").start();

        new Thread(() -> {
            while (true) {
                Reference<? extends MyObejct> myObject = referenceQueue.poll();
                if (myObject != null) {
                    System.out.println("虚对象回收，加入了队列");
                }

            }
        }, "thread-2").start();

    }

    // 弱引用 生命周期比软引用还短
    private static void weakReference() {
        WeakReference<MyObejct> weakReference = new WeakReference<>(new MyObejct());
        System.gc();

        System.out.println("gc after 内存够用 " + weakReference.get());

        try {
            byte[] bytes = new byte[20 * 1024 * 1024]; // 20M

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("gc after 内存不够用 " + weakReference.get());

        }
    }

    // 软引用 内存够用，不回收
    private static void softReference() {
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
