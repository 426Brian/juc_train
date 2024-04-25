package lock;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: WaitNotifyDemo
 * Pacage: com.juc.lock
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/10-14:56
 * Version: V1.0
 */
public class WaitNotifyDemo {
    public static void main(String[] args) {
        Object obj = new Object();
        new Thread(() -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + " come in");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " 被唤醒");

            }
        }, "t_AA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(() -> {
            synchronized (obj) {
                obj.notify();
                System.out.println(Thread.currentThread().getName() + " 发出通知");
            }
        }, "t_BB").start();
    }
}
