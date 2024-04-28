package com.juc;

/**
 * Classname: DaemonTest
 * Pacage: com.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/25-20:20
 * Version: v1.0
 */
public class DaemonTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {

            }
        });
        thread.setDaemon(true); // 守护线程
        thread.start();

        System.out.println(Thread.currentThread().getName() + " stop ");
    }
}
