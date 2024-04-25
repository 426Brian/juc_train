package lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: ReEntryLockDemo
 * Pacage: com.juc.lock
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/10-9:52
 * Version: V1.0
 */
public class ReEntryLockDemo {
    static final Object obj = new Object();
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
//        reEntryLock();
//        m1();
        lockDemo();
    }

    private static void reEntryLock() {
        new Thread(() -> {
            synchronized (obj) {
                System.out.println("外层synchronized (obj)");
                synchronized (obj) {
                    System.out.println("中层synchronized (obj)");
                    synchronized (obj) {
                        System.out.println("内层synchronized (obj)");
                    }
                }
            }
        }, "AA").start();
    }

    public static synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " come in m1()");
        m2();
    }

    public static synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + " come in m2()");
        m3();
    }

    public static synchronized void m3() {
        System.out.println(Thread.currentThread().getName() + " come in m3()");
    }

    public static void lockDemo() {
        new Thread(() -> {
            lock.lock();
            try {
                try {
                    System.out.println(Thread.currentThread().getName() + " 外层");
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " 内层");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }, "AA").start();
        new Thread(() -> {
            lock.lock();
            try {
                try {
                    System.out.println(Thread.currentThread().getName() + " 外层");
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " 内层");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
//                    lock.unlock();
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }, "BB").start();
    }

}
