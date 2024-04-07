package com.juc;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: LockTest2
 * Pacage: com.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/7-11:51
 * Version: V1.0
 */
public class LockTest2 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "AA").start();
        Thread.sleep(100);
        new Thread(() -> {
            phone2.sendEmail();
        }, "BB").start();
    }
}

class Phone {
    public synchronized void sendSMS() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("--- send sms");
    }

    public synchronized void sendEmail() {
        System.out.println("--- sendEmail");
    }

    public void getHello() {
        System.out.println("--- getHello");
    }

}